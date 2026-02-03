package com.cso.chirp.api.mappers

import com.cso.chirp.api.dto.ChatDto
import com.cso.chirp.api.dto.ChatMessageDto
import com.cso.chirp.api.dto.ChatParticipantDto
import com.cso.chirp.domain.model.Chat
import com.cso.chirp.domain.model.ChatMessage
import com.cso.chirp.domain.model.ChatParticipant

fun Chat.toChatDto(): ChatDto {
    return ChatDto(
        id = id,
        participants = participants.map { it.toChatParticipantDto() },
        lastActivityAt = lastActivityAt,
        lastMessage = lastMessage?.toChatMessageDto(),
        creator = creator.toChatParticipantDto()
    )
}

fun ChatParticipant.toChatParticipantDto(): ChatParticipantDto {
    return ChatParticipantDto(
        userId = userId,
        username = username,
        email = email,
        profilePictureUrl = profilePictureUrl
    )
}

fun ChatMessage.toChatMessageDto(): ChatMessageDto {
    return ChatMessageDto(
        id = id,
        chatId = chatId,
        content = content,
        createdAt = createdAt,
        senderId = sender.userId
    )
}