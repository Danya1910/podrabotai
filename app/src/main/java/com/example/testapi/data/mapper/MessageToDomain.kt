package com.example.testapi.data.mapper

import com.example.testapi.data.dto.response.MessageDto
import com.example.testapi.domain.model.Message

fun MessageDto.toDomain() : Message {
    return Message(
        createdAt = created_at,
        isReaded = is_readed,
        senderId = sender_id,
        text = text
    )
}