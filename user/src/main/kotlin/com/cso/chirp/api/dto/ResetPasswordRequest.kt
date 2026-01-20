package com.cso.chirp.api.dto

import com.cso.chirp.api.util.Password
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank

data class ResetPasswordRequest(
    @field:NotBlank
    @JsonProperty("token")
    val token: String,
    @Password
    @JsonProperty("newPassword")
    val newPassword: String
)