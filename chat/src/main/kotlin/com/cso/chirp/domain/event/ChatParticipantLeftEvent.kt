package com.cso.chirp.domain.event

import com.cso.chirp.domain.type.ChatId
import com.cso.chirp.domain.type.UserId

data class ChatParticipantLeftEvent(
    val chatId: ChatId,
    val userId: UserId
)