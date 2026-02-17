package com.cso.chirp.infra.mappers

import com.cso.chirp.domain.model.DeviceToken
import com.cso.chirp.infra.database.entity.DeviceTokenEntity

fun DeviceTokenEntity.toDeviceToken(): DeviceToken {
    return DeviceToken(
        userId = userId,
        token = token,
        platform = platform.toPlatform(),
        createdAt = createdAt,
        id = id
    )
}