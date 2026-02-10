package com.cso.chirp.api.dto.ws

import com.cso.chirp.domain.type.ChatId

data class ChatParticipantsChangedDto(
    val chatId: ChatId,
)
