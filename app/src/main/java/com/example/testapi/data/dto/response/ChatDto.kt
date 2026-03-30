package com.example.testapi.data.dto.response

import kotlinx.coroutines.Job

data class ChatDto(
    val job_id: Int,
    val name: String,
    val penpal_id: Int
)
