package com.example.testapi.data.mapper

import com.example.testapi.data.dto.response.AdvertisementResponseDto
import com.example.testapi.domain.model.Advertisement

fun AdvertisementResponseDto.toDomain () : Advertisement {
    return Advertisement(
        address = address,
        car = car,
        city = city,
        id = id,
        isFavorite = is_favorite,
        isUrgent = is_urgent,
        salary = salary,
        timeEnd = time_end,
        timeStart = time_start,
        title = title,
        user = user.toDomain(),
    )
}