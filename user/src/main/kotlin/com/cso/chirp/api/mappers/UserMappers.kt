package com.cso.chirp.api.mappers

import com.cso.chirp.api.dto.AuthenticatedUserDto
import com.cso.chirp.api.dto.UserDto
import com.cso.chirp.domain.model.AuthenticatedUser
import com.cso.chirp.domain.model.User

fun User.toUserDto(): UserDto = UserDto(
    id = id,
    username = username,
    email = email,
    hasEmailVerified = hasEmailVerified
)

fun AuthenticatedUser.toAuthenticatedUserDto(): AuthenticatedUserDto = AuthenticatedUserDto(
    user = user.toUserDto(),
    accessToken = accessToken,
    refreshToken = refreshToken
)

fun UserDto.toUser(): User = User(
    id = id,
    username = username,
    email = email,
    hasEmailVerified = hasEmailVerified
)

fun AuthenticatedUserDto.toAuthenticatedUser(): AuthenticatedUser = AuthenticatedUser(
    user = user.toUser(),
    accessToken = accessToken,
    refreshToken = refreshToken
)
