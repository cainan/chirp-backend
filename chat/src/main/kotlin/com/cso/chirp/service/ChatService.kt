package com.cso.chirp.service

import com.cso.chirp.api.dto.ChatMessageDto
import com.cso.chirp.api.mappers.toChatMessageDto
import com.cso.chirp.domain.exception.ChatNotFoundException
import com.cso.chirp.domain.exception.ChatParticipantNotFoundException
import com.cso.chirp.domain.exception.ForbiddenException
import com.cso.chirp.domain.exception.InvalidChatSizeException
import com.cso.chirp.domain.model.Chat
import com.cso.chirp.domain.model.ChatMessage
import com.cso.chirp.domain.type.ChatId
import com.cso.chirp.domain.type.UserId
import com.cso.chirp.infra.database.entities.ChatEntity
import com.cso.chirp.infra.database.mappers.toChat
import com.cso.chirp.infra.database.mappers.toChatMessage
import com.cso.chirp.infra.database.repositories.ChatMessageRepository
import com.cso.chirp.infra.database.repositories.ChatParticipantRepository
import com.cso.chirp.infra.database.repositories.ChatRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

@Service
class ChatService(
    private val chatRepository: ChatRepository,
    private val chatParticipantRepository: ChatParticipantRepository,
    private val chatMessageRepository: ChatMessageRepository
) {

    @Transactional
    fun createChat(
        creatorId: UserId,
        otherUserIds: Set<UserId>,
    ): Chat {

        val otherParticipants = chatParticipantRepository.findByUserIdIn(otherUserIds)

        val allParticipants = (otherParticipants + creatorId)
        if (allParticipants.size < 2) {
            throw InvalidChatSizeException()
        }

        val creator = chatParticipantRepository.findByIdOrNull(creatorId)
            ?: throw InvalidChatSizeException()

        return chatRepository.save(
            ChatEntity(
                creator = creator,
                participants = otherParticipants.toMutableSet().apply { add(creator) },
            )
        ).toChat()
    }

    @Transactional
    fun addParticipantsToChat(
        requestUserId: UserId,
        chatId: ChatId,
        otherUserIds: Set<UserId>,
    ): Chat {
        val chat = chatRepository.findByIdOrNull(chatId)
            ?: throw ChatNotFoundException()

        val isRequestingUserInChat = chat.participants.any {
            it.userId == requestUserId
        }

        if (!isRequestingUserInChat) {
            throw ForbiddenException()
        }

        val otherUsers = otherUserIds.map { userId ->
            chatParticipantRepository.findByIdOrNull(userId)
                ?: throw ChatParticipantNotFoundException(userId)
        }

        val lastMessage = lastMessageForChat(chatId)

        val updatedChat = chatRepository.save(
            chat.apply {
                this.participants = (chat.participants + otherUsers).toMutableSet()
            }
        ).toChat(lastMessage)

        return updatedChat
    }

    @Transactional
    fun removeParticipantFromChat(
        chatId: ChatId,
        userId: UserId
    ) {
        val chat = chatRepository.findByIdOrNull(chatId)
            ?: throw ChatNotFoundException()
        val participant = chat.participants.find { it.userId == userId }
            ?: throw ChatParticipantNotFoundException(userId)

        val newParticipantsSize = chat.participants.size - 1
        if (newParticipantsSize == 0) {
            chatRepository.deleteById(chatId)
            return
        }

        chatRepository.save(
            chat.apply {
                this.participants = (chat.participants - participant).toMutableSet()
            }
        )
    }

    private fun lastMessageForChat(chatId: ChatId): ChatMessage? {
        return chatMessageRepository
            .findLatestMessagesByChatIds(setOf(chatId))
            .firstOrNull()
            ?.toChatMessage()
    }

    fun getChatMessages(
        chatId: ChatId,
        before: Instant?,
        pageSize: Int
    ): List<ChatMessageDto> {
        return chatMessageRepository
            .findByChatIdBefore(
                chatId = chatId,
                before = before ?: Instant.now(),
                pageable = PageRequest.of(0, pageSize)
            )
            .content
            .asReversed()
            .map { it.toChatMessage().toChatMessageDto() }
    }

}