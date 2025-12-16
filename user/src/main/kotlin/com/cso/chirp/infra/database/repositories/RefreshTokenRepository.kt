package com.cso.chirp.infra.database.repositories

import com.cso.chirp.domain.model.UserId
import com.cso.chirp.infra.database.entities.RefreshTokenEntity
import org.springframework.data.jpa.repository.JpaRepository

interface RefreshTokenRepository : JpaRepository<RefreshTokenEntity, Long> {
    fun findByUserIdAndHashedToken(userId: UserId, hashedToken: String): RefreshTokenEntity?
    fun deleteByUserId(userId: UserId)
    fun deleteByUserIdAndHashedToken(userId: UserId, hashedToken: String)
}