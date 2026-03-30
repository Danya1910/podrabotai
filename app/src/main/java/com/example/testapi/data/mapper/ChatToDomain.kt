package com.example.testapi.data.mapper

import com.example.testapi.data.dto.response.ChatDto
import com.example.testapi.domain.model.Chat

fun ChatDto.toDomain() : Chat {
    return Chat(
        jobId = job_id,
        name = name,
        penpalId = penpal_id
    )
}