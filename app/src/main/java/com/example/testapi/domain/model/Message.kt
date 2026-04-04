package com.example.testapi.domain.model

data class Message(
    val createdAt: String,
    val isRead: Boolean,
    val senderId: Int,
    val text: String
)
