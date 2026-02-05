package com.cso.chirp.domain.event

import com.cso.chirp.domain.type.ChatId
import com.cso.chirp.domain.type.ChatMessageId

data class MessageDeletedEvent(
    val chatId: ChatId,
    val messageId: ChatMessageId,
)