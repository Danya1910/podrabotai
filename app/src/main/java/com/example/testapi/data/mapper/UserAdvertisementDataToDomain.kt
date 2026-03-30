package com.example.testapi.data.mapper

import com.example.testapi.data.dto.response.UserAdvertisementDataDto
import com.example.testapi.domain.model.UserAdvertisementData

fun UserAdvertisementDataDto.toDomain() : UserAdvertisementData{
    return UserAdvertisementData(
        id = id,
        phone = phone,
        photo = photo,
        userName = user_name
    )
}