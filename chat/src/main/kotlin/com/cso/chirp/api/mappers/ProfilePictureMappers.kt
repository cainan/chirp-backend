package com.cso.chirp.api.mappers

import com.cso.chirp.api.dto.PictureUploadResponse
import com.cso.chirp.domain.model.ProfilePictureUploadCredentials

fun ProfilePictureUploadCredentials.toResponse(): PictureUploadResponse {
    return PictureUploadResponse(
        uploadUrl = uploadUrl,
        publicUrl = publicUrl,
        headers = headers,
        expiresAt = expiresAt
    )
}