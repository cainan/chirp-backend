package com.cso.chirp.domain.exception

class UnauthorizedException :
    RuntimeException("Missing auth details")