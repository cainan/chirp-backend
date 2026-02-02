package com.cso.chirp.infra.database.mappers

import com.cso.chirp.domain.model.Chat
import com.cso.chirp.domain.model.ChatMessage
import com.cso.chirp.domain.model.ChatParticipant
import com.cso.chirp.infra.database.entities.ChatEntity
import com.cso.chirp.infra.database.entities.ChatParticipantEntity

fun ChatEntity.toChat(lastMessage: ChatMessage? = null): Chat {
    return Chat(
        id = id!!,
        participants = participants.map {
            it.toChatParticipant()
        }.toSet(),
        creator = creator.toChatParticipant(),
        lastMessage = lastMessage,
        lastActivityAt = lastMessage?.createdAt ?: createdAt,
        createdAt = createdAt
    )
}

fun ChatParticipantEntity.toChatParticipant(): ChatParticipant {
    return ChatParticipant(
        userId = userId,
        username = username,
        email = email,
        profilePictureUrl = profilePictureUrl
    )
}

