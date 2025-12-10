package com.cso.user.domain.model

data class AuthenticatedUser(
    val user: User,
    val accessToken: String
)
