package com.cso.chirp.infra

import com.cso.chirp.domain.events.user.UserEvent
import com.cso.chirp.infra.message_queue.MessageQueues
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class NotificationUserEventListener {

    @RabbitListener(queues = [MessageQueues.NOTIFICATION_USER_EVENTS])
    fun handleUserEvent(event: UserEvent) {
        when (event) {
            is UserEvent.Created -> {
                println("UserEvent received: Created")
            }
            is UserEvent.RequestResendVerification -> {
                println("UserEvent received: RequestResendVerification")
            }
            is UserEvent.RequestResetPassword -> {
                println("UserEvent received: RequestResetPassword")
            }
            else -> Unit
        }
    }
}