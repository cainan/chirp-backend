package com.cso.chirp.api.controllers

import com.cso.chirp.api.dto.AddParticipantToChatDto
import com.cso.chirp.api.dto.ChatDto
import com.cso.chirp.api.dto.ChatMessageDto
import com.cso.chirp.api.dto.CreateChatRequest
import com.cso.chirp.api.mappers.toChatDto
import com.cso.chirp.api.utils.requestUserId
import com.cso.chirp.domain.type.ChatId
import com.cso.chirp.service.ChatService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.time.Instant

@RestController
@RequestMapping("api/chat")
class ChatController(private val chatService: ChatService) {

    companion object {
        private const val DEFAULT_PAGE_SIZE = 20
    }

    @GetMapping("/{chatId}/messages")
    fun getMessagesForChat(
        @PathVariable chatId: ChatId,
        @RequestParam(required = false) before: Instant? = null,
        @RequestParam(required = false) pageSize: Int = DEFAULT_PAGE_SIZE,
    ): List<ChatMessageDto> {
        return chatService.getChatMessages(
            chatId = chatId,
            before = before,
            pageSize = pageSize
        )
    }

    @PostMapping
    fun createChat(
        @Valid @RequestBody body: CreateChatRequest
    ): ChatDto {
        return chatService.createChat(
            creatorId = requestUserId,
            otherUserIds = body.otherUserIds.toSet()
        ).toChatDto()
    }

    @GetMapping("/{chatId}")
    fun getChat(
        @PathVariable chatId: ChatId,
    ): ChatDto {
        return chatService.getChatById(
            chatId = chatId,
            requestUserId = requestUserId
        )?.toChatDto() ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    @GetMapping
    fun getChatsForUser(): List<ChatDto> {
        return chatService.findChatsByUser(
            userId = requestUserId,
        ).map { it.toChatDto() }
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