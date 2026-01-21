package com.cso.chirp.api.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.Email

data class EmailRequest(
    @field:Email
    @JsonProperty("email")
    val email: String
)