package com.cso.chirp.infra.database.entities

import com.cso.chirp.domain.type.ChatId
import com.cso.chirp.domain.type.ChatMessageId
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.Instant

@Entity
@Table(
    name = "chat_messages",
    schema = "chat_service",
    indexes = [
        Index(name = "idx_chat_messages_chat_id_created_at", columnList = "chat_id,created_at DESC")
    ]
)
class ChatMessageEntity(
    @Id
    var id: ChatMessageId? = null,

    @Column(nullable = false)
    var content: String,

    @Column(
        name = "chat_id",
        nullable = false,
        updatable = false
    )
    var chatId: ChatId,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "chat_id",
        nullable = false,
        insertable = false,
        updatable = false
    )
    var chat: ChatEntity? = null,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
        name = "sender_id",
        nullable = false,
    )
    var sender: ChatParticipantEntity,

    @CreationTimestamp
    var createdAt: Instant = Instant.now(),
)