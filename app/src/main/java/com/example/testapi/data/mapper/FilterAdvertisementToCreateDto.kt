package com.example.testapi.data.mapper

import com.example.testapi.data.dto.request.AdvertisementFilterDto
import com.example.testapi.domain.model.AdvertisementFilter

fun AdvertisementFilter.toCreateDto() : AdvertisementFilterDto {
    return AdvertisementFilterDto(
        car = car,
        salary = salary,
        age = age,
        xp = xp,
        address = address,
        date = date
    )
}