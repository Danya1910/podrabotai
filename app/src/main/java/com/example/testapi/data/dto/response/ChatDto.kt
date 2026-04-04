package com.example.testapi.data.dto.response

import kotlinx.coroutines.Job

data class ChatDto(
    val job: ChatJobDto,
    val penpal: ChatPenpalDto,
)
