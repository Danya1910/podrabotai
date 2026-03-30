package com.example.testapi.data.mapper

import com.example.testapi.data.dto.request.CreateAdvertisementRequestDto
import com.example.testapi.domain.model.CreateAdvertisementRequest

fun CreateAdvertisementRequest.toCreateDto() : CreateAdvertisementRequestDto{
    return CreateAdvertisementRequestDto(
        title = title,
        wanted_job = wantedJob,
        description = description,
        salary = salary,
        date = date,
        time_start = timeStart,
        time_end = timeEnd,
        address = address,
        city_id = cityId,
        city = city,
        xp = xp,
        age = age,
        is_urgent = isUrgent,
        car = car
    )
}