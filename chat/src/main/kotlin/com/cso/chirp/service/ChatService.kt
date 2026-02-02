package com.cso.chirp.service

import com.cso.chirp.domain.exception.InvalidChatSizeException
import com.cso.chirp.domain.model.Chat
import com.cso.chirp.domain.type.UserId
import com.cso.chirp.infra.database.entities.ChatEntity
import com.cso.chirp.infra.database.mappers.toChat
import com.cso.chirp.infra.database.repositories.ChatParticipantRepository
import com.cso.chirp.infra.database.repositories.ChatRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ChatService(
    private val chatRepository: ChatRepository,
    private val chatParticipantRepository: ChatParticipantRepository
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
}