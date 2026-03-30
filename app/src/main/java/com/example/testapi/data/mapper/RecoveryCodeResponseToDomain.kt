package com.example.testapi.data.mapper

import com.example.testapi.data.dto.response.RecoveryCodeResponseDto
import com.example.testapi.domain.model.RecoveryCodeResponse

fun RecoveryCodeResponseDto.toDomain() : RecoveryCodeResponse {
    return RecoveryCodeResponse(
        message = message,
        statusCode = 0
    )
}