package com.example.testapi.data.mapper

import com.example.testapi.data.dto.response.ChatDto
import com.example.testapi.domain.model.Chat

fun ChatDto.toDomain() : Chat {
    return Chat(
        job = job.toDomain(),
        penpal = penpal.toDomain()
    )
}