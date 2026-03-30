package com.example.testapi.data.mapper

import com.example.testapi.data.dto.request.SendMessageRequestDto
import com.example.testapi.domain.model.SendMessageRequest

fun SendMessageRequest.toCreateDto() : SendMessageRequestDto {
    return SendMessageRequestDto(
        text = text
    )
}