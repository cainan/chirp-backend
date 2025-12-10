package com.cso.chirp.domain.model

data class AuthenticatedUser(
    val user: User,
    val accessToken: String
)
