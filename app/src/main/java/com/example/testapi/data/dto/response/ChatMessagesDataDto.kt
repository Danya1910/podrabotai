package com.example.testapi.data.dto.response

import com.example.testapi.domain.model.ChatMessages

data class ChatMessagesDataDto(
    val chat: ChatDto,
    val messages: List<MessageDto>
)
