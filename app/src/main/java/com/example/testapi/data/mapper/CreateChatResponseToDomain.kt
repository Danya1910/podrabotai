package com.example.testapi.data.mapper

import com.example.testapi.data.dto.response.CreateChatResponseDto
import com.example.testapi.domain.model.CreateChatRequest
import com.example.testapi.domain.model.CreateChatResponse

fun CreateChatResponseDto.toDomain() : CreateChatResponse {
    return CreateChatResponse(
        message = message
    )
}