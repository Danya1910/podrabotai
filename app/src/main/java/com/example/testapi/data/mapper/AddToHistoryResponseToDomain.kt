package com.example.testapi.data.mapper

import com.example.testapi.data.dto.response.AddToHistoryResponseDto
import com.example.testapi.domain.model.AddToHistoryResponse

fun AddToHistoryResponseDto.toDomain() : AddToHistoryResponse {
    return AddToHistoryResponse(
        jobId = data.job_id,
        message = data.message
    )
}