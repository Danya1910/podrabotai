package com.example.testapi.data.mapper

import com.example.testapi.data.dto.response.ConfirmEmailResponseDto
import com.example.testapi.domain.model.ConfirmEmailResponse

fun ConfirmEmailResponseDto.toDomain(): ConfirmEmailResponse {
    return ConfirmEmailResponse(
        message = message,
        accessToken = accessToken,
        role = role
    )
}