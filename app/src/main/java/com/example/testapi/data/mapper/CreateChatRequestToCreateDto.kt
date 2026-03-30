package com.example.testapi.data.mapper

import com.example.testapi.data.dto.request.CreateChatRequestDto
import com.example.testapi.domain.model.CreateChatRequest

fun CreateChatRequest.toCreateDto(): CreateChatRequestDto {
    return CreateChatRequestDto(
        penpal_id = penpalId,
        job_id = jobId
    )
}