package com.cso.chirp.infra.mappers

import com.cso.chirp.domain.model.DeviceToken
import com.cso.chirp.infra.database.entity.PlatformEntity

fun DeviceToken.Platform.toPlatformEntity(): PlatformEntity {
    return when(this) {
        DeviceToken.Platform.ANDROID -> PlatformEntity.ANDROID
        DeviceToken.Platform.IOS -> PlatformEntity.IOS
    }
}

fun PlatformEntity.toPlatform(): DeviceToken.Platform {
    return when(this) {
        PlatformEntity.ANDROID -> DeviceToken.Platform.ANDROID
        PlatformEntity.IOS -> DeviceToken.Platform.IOS
    }
}