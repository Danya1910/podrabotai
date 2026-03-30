package com.example.testapi.data.mapper

import com.example.testapi.data.dto.response.CreateAdvertisementResponseDto
import com.example.testapi.domain.model.CreateAdvertisementResponse

fun CreateAdvertisementResponseDto.toDomain() : CreateAdvertisementResponse{
    return CreateAdvertisementResponse(
        jobId = job_id,
        title = title,
        wantedJob = wanted_job,
        salary = salary,
        timeStart = time_start,
        timeEnd = time_end,
        createdAt = created_at,
        address = address,
        city = city,
        car = car,
        message = message
    )
}