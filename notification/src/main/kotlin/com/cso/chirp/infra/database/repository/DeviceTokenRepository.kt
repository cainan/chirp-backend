package com.cso.chirp.infra.database.repository

import com.cso.chirp.domain.type.UserId
import com.cso.chirp.infra.database.entity.DeviceTokenEntity
import org.springframework.data.jpa.repository.JpaRepository

interface DeviceTokenRepository: JpaRepository<DeviceTokenEntity, Long> {
    fun findByUserIdIn(userIds: List<UserId>): List<DeviceTokenEntity>
    fun findByToken(token: String): DeviceTokenEntity?
    fun deleteByToken(token: String)
}