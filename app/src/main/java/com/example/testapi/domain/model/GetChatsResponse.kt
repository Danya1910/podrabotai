package com.example.testapi.domain.model

data class GetChatsResponse (
    val jobId: Int,
    val jobName: String,
    val lastMessage: LastMessageData?,
    val penpalAvatar: String?,
    val penpalId: Int,
    val penpalName: String,
    val unreadMessages: Int
)
