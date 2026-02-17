package com.cso.chirp.infra.message_queue

import com.cso.chirp.domain.events.chat.ChatEvent
import com.cso.chirp.service.PushNotificationService
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class NotificationChatEventListener(
    private val pushNotificationService: PushNotificationService
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @RabbitListener(queues = [MessageQueues.NOTIFICATION_CHAT_EVENTS])
    fun handleUserEvent(event: ChatEvent) {
        logger.info("ChatEvent received: $event")
        when (event) {
            is ChatEvent.NewMessage -> {
                pushNotificationService.sendNewMessageNotifications(
                    recipientUserIds = event.recipientIds.toList(),
                    senderUserId = event.senderId,
                    senderUsername = event.senderUsername,
                    message = event.message,
                    chatId = event.chatId
                )
            }
        }
    }
}