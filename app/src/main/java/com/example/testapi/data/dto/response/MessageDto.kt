package com.example.testapi.data.dto.response

data class MessageDto(
    val created_at: String,
    val is_readed: Boolean,
    val sender_id: Int,
    val text: String
)
