package com.cso.chirp.api.dto

import com.cso.chirp.domain.type.UserId

data class UserDto(
    val id: UserId,
    val username: String,
    val email: String,
    val hasEmailVerified: Boolean
)