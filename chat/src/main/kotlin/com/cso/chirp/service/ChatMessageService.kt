package com.cso.chirp.service

import com.cso.chirp.domain.exception.ChatNotFoundException
import com.cso.chirp.domain.exception.ChatParticipantNotFoundException
import com.cso.chirp.domain.exception.ForbiddenException
import com.cso.chirp.domain.exception.MessageNotFoundException
import com.cso.chirp.domain.model.ChatMessage
import com.cso.chirp.domain.type.ChatId
import com.cso.chirp.domain.type.ChatMessageId
import com.cso.chirp.domain.type.UserId
import com.cso.chirp.infra.database.entities.ChatMessageEntity
import com.cso.chirp.infra.database.mappers.toChatMessage
import com.cso.chirp.infra.database.repositories.ChatMessageRepository
import com.cso.chirp.infra.database.repositories.ChatParticipantRepository
import com.cso.chirp.infra.database.repositories.ChatRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ChatMessageService(
    private val chatMessageRepository: ChatMessageRepository,
    private val chatRepository: ChatRepository,
    private val chatParticipantRepository: ChatParticipantRepository,
) {

    @Transactional
    fun sendMessage(
        chatId: ChatId,
        senderId: UserId,
        content: String,
        messageId: ChatMessageId? = null
    ): ChatMessage {
        val chat = chatRepository.findChatById(chatId, senderId)
            ?: throw ChatNotFoundException()

        val sender = chatParticipantRepository.findByIdOrNull(senderId)
            ?: throw ChatParticipantNotFoundException(senderId)

        val savedMessage = chatMessageRepository.save(
            ChatMessageEntity(
                id = messageId,
                content = content.trim(),
                chatId = chatId,
                chat = chat,
                sender = sender,
            )
        )

        return savedMessage.toChatMessage()
    }

    fun deleteMessage(
        messageId: ChatMessageId,
        requestUserId: UserId
    ) {
        val message = chatMessageRepository.findByIdOrNull(messageId)
            ?: throw MessageNotFoundException(messageId)

        if (message.sender.userId != requestUserId) {
            throw ForbiddenException()
        }

        chatMessageRepository.delete(message)
    }
}