package com.cso.chirp.api.dto.ws

import com.cso.chirp.domain.type.ChatId
import com.cso.chirp.domain.type.ChatMessageId

data class SendMessageDto(
    val chatId: ChatId,
    val content: String,
    val messageId: ChatMessageId? = null
)
