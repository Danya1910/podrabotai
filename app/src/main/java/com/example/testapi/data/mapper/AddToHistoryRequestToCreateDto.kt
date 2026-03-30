package com.example.testapi.data.mapper

import com.example.testapi.data.dto.request.AddToHistoryRequestDto
import com.example.testapi.domain.model.AddToHistoryRequest

fun AddToHistoryRequest.toCreateDto() : AddToHistoryRequestDto {
    return AddToHistoryRequestDto(
        job_id = jobId
    )
}