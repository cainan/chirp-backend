package com.cso.chirp.api.dto.ws

import com.cso.chirp.domain.type.ChatId
import com.cso.chirp.domain.type.ChatMessageId

data class SendMessageDto(
    val chatId: ChatId,
    val content: String,
    val messageId: ChatMessageId? = null
)
/*
{\"chatId\": \"ac5ff6a9-7c85-442a-9da0-b4b60ac6c077\",\"content\": \"E ai manooooo\",\"mesageId\": \"\"}
 */