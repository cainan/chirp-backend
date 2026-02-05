package com.cso.chirp.domain.exception

import com.cso.chirp.domain.type.ChatMessageId

class MessageNotFoundException(
    private val id: ChatMessageId
) : RuntimeException(
    "Message with ID $id not found"
)