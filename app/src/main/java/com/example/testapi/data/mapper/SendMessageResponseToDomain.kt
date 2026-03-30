package com.example.testapi.data.mapper

import com.example.testapi.data.dto.request.SendMessageResponseDto
import com.example.testapi.domain.model.SendMessageResponse

fun SendMessageResponseDto.toDomain() : SendMessageResponse {
    return SendMessageResponse(
        data = data
    )
}