package com.cso.chirp.api.util

import com.cso.chirp.domain.exception.UnauthorizedException
import com.cso.com.cso.chirp.domain.type.UserId
import org.springframework.security.core.context.SecurityContextHolder

val requestUserId: UserId
    get() = SecurityContextHolder.getContext().authentication?.principal as? UserId
        ?: throw UnauthorizedException()