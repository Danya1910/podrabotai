package com.example.testapi.data.mapper

import com.example.testapi.data.dto.response.DetailedAdvertisementResponseDto
import com.example.testapi.domain.model.DetailedAdvertisement

fun DetailedAdvertisementResponseDto.toDomain() : DetailedAdvertisement {
    return DetailedAdvertisement(
        address = address,
        age = age,
        car = car,
        city = city,
        createdAt = created_at,
        date = date,
        description = description,
        id = id,
        isFavorite = is_favorite,
        isUrgent = is_urgent,
        salary = salary,
        status = status,
        timeEnd = time_end,
        timeStart = time_start,
        title = title,
        user = user.toDomain(),
        wantedJob = wanted_job,
        xp = xp
    )
}