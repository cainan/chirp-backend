package com.cso.chirp.api.controllers

import com.cso.chirp.api.dto.ChatDto
import com.cso.chirp.api.dto.CreateChatRequest
import com.cso.chirp.api.mappers.toChatDto
import com.cso.chirp.api.utils.requestUserId
import com.cso.chirp.service.ChatService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/chat")
class ChatController(private val chatService: ChatService) {

    @PostMapping
    fun createChat(
        @Valid @RequestBody body: CreateChatRequest
    ): ChatDto {
        return chatService.createChat(
            creatorId = requestUserId,
            otherUserIds = body.otherUserIds.toSet()
        ).toChatDto()
    }
}