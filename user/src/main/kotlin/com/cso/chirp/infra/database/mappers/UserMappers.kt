package com.cso.chirp.infra.database.mappers

import com.cso.chirp.domain.model.User
import com.cso.chirp.infra.database.entities.UserEntity

fun UserEntity.toUser() = User(
    id = id!!,
    username = username,
    email = email,
    hasEmailVerified = hasVerifiedEmail
)
