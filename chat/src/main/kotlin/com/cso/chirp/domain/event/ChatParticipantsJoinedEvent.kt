package com.cso.chirp.domain.event

import com.cso.chirp.domain.type.ChatId
import com.cso.chirp.domain.type.UserId

data class ChatParticipantsJoinedEvent(
    val chatId: ChatId,
    val userIds: Set<UserId>
)