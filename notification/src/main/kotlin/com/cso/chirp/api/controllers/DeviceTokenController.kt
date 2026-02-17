package com.cso.chirp.api.controllers

import com.cso.chirp.api.dto.DeviceTokenDto
import com.cso.chirp.api.dto.RegisterDeviceRequest
import com.cso.chirp.api.mappers.toDeviceTokenDto
import com.cso.chirp.api.mappers.toPlatform
import com.cso.chirp.api.utils.requestUserId
import com.cso.chirp.service.PushNotificationService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/notification")
class DeviceTokenController(
    private val pushNotificationService: PushNotificationService
) {

    @PostMapping("/register")
    fun registerDeviceToken(
        @Valid @RequestBody body: RegisterDeviceRequest
    ): DeviceTokenDto {
        return pushNotificationService.registerDevice(
            userId = requestUserId,
            token = body.token,
            platform = body.platform.toPlatform()
        ).toDeviceTokenDto()
    }

}