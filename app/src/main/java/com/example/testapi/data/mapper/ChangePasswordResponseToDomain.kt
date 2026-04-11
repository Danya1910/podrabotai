package com.example.testapi.data.mapper

import com.example.testapi.data.dto.response.ChangePasswordResponseDto
import com.example.testapi.domain.model.ChangePasswordResponse

fun ChangePasswordResponseDto.toDomain() : ChangePasswordResponse {
    return ChangePasswordResponse(
        message = message,
        accessToken = access_token,
        role = role
    )
}