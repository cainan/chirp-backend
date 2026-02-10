package com.cso.chirp.api.dto.ws

import com.cso.chirp.domain.type.ChatId
import com.cso.chirp.domain.type.ChatMessageId

data class DeleteMessageDto(
    val chatId: ChatId,
    val messageId: ChatMessageId
)