package com.cso.chirp.domain.model

import com.cso.chirp.domain.type.ChatId
import com.cso.chirp.domain.type.ChatMessageId
import java.time.Instant

data class ChatMessage(
    val id: ChatMessageId,
    val chatId: ChatId,
    val sender: ChatParticipant,
    val content: String,
    val createdAt: Instant,
)
