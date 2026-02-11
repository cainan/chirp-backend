package com.cso.chirp.api.dto.ws

import com.cso.chirp.domain.type.UserId

data class ProfilePictureUpdateDto(
    val userId: UserId,
    val newUrl: String?
)