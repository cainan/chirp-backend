package com.cso.chirp.domain.event

import com.cso.chirp.domain.type.ChatId
import com.cso.chirp.domain.type.UserId

data class ChatCreatedEvent(
    val chatId: ChatId,
    val participants: List<UserId>
)