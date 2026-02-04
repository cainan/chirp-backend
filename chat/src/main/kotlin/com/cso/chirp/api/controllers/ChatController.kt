package com.cso.chirp.api.controllers

import com.cso.chirp.api.dto.AddParticipantToChatDto
import com.cso.chirp.api.dto.ChatDto
import com.cso.chirp.api.dto.CreateChatRequest
import com.cso.chirp.api.mappers.toChatDto
import com.cso.chirp.api.utils.requestUserId
import com.cso.chirp.domain.type.ChatId
import com.cso.chirp.service.ChatService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

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

    @PostMapping("/{chatId}/add")
    fun addChatParticipants(
        @PathVariable chatId: ChatId,
        @Valid @RequestBody body: AddParticipantToChatDto
    ): ChatDto {
        return chatService.addParticipantsToChat(
            requestUserId = requestUserId,
            chatId = chatId,
            otherUserIds = body.userIds.toSet(),
        ).toChatDto()
    }

    @DeleteMapping("/{chatId}/leave")
    fun leaveChat(
        @PathVariable chatId: ChatId
    ) {
        chatService.removeParticipantFromChat(
            chatId = chatId,
            userId = requestUserId
        )
    }

}