package com.example.testapi.data.dto.response

data class GetChatsResponseDto(
    val job_id: Int,
    val job_name: String,
    val last_message_data: LastMessageDataDto?,
    val penpal_avatar: String?,
    val penpal_id: Int,
    val penpal_name: String,
    val unread_messages: Int
)
