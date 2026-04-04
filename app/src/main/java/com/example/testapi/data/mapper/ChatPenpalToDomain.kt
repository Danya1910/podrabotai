package com.example.testapi.data.mapper

import com.example.testapi.data.dto.response.ChatPenpalDto
import com.example.testapi.domain.model.ChatPenpal

fun ChatPenpalDto.toDomain() : ChatPenpal {
    return ChatPenpal(
        avatar = avatar,
        id = id,
        name = name
    )
}