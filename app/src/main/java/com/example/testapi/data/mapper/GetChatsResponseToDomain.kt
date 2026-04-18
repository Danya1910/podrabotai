package com.example.testapi.data.mapper

import com.example.testapi.data.dto.response.GetChatsResponseDto
import com.example.testapi.data.dto.response.LastMessageDataDto
import com.example.testapi.domain.model.GetChatsResponse
import com.example.testapi.domain.model.LastMessageData

fun GetChatsResponseDto.toDomain() : GetChatsResponse {
    return GetChatsResponse(
        jobId = job_id,
        jobName = job_name,
        lastMessage = last_message_data?.toDomain(),
        penpalAvatar = penpal_avatar,
        penpalId = penpal_id,
        penpalName = penpal_name,
        unreadMessages = unread_messages
    )
}

fun LastMessageDataDto.toDomain() : LastMessageData {
    return LastMessageData(
        createdAt = created_at,
        senderId = sender_id,
        text = text
    )
}