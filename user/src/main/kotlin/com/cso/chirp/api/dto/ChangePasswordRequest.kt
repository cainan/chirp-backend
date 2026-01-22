package com.cso.chirp.api.dto

import com.cso.chirp.api.util.Password
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank

data class ChangePasswordRequest(
    @field:NotBlank
    @JsonProperty("oldPassword")
    val oldPassword: String,
    @field:Password
    @JsonProperty("newPassword")
    val newPassword: String
)