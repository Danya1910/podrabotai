package com.example.testapi.data.mapper

import com.example.testapi.data.dto.response.ChatJobDto
import com.example.testapi.domain.model.ChatJob

fun ChatJobDto.toDomain() : ChatJob {
    return ChatJob(
        address = address,
        car = car,
        isUrgent = is_urgent,
        salary = salary,
        timeEnd = time_end,
        timeStart = time_start,
        title = title
    )
}