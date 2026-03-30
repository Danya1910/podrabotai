package com.example.testapi.data.mapper

import com.example.testapi.data.dto.response.ChatMessagesResponseDto
import com.example.testapi.domain.model.ChatMessages

fun ChatMessagesResponseDto.toDomain() : ChatMessages {
    return ChatMessages(
        chat = data.chat.toDomain(),
        messages = data.messages.map{it.toDomain()}
    )
}