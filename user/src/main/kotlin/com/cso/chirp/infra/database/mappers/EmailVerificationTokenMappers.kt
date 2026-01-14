package com.cso.chirp.infra.database.mappers

import com.cso.chirp.domain.model.EmailVerificationToken
import com.cso.chirp.infra.database.entities.EmailVerificationTokenEntity

fun EmailVerificationTokenEntity.toEmailVerificationToken(): EmailVerificationToken {
    return EmailVerificationToken(
        id = id,
        token = token,
        user = user.toUser()
    )
}