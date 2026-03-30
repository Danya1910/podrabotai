package com.example.testapi.data.mapper

import com.example.testapi.data.dto.response.PlatformDataDto
import com.example.testapi.domain.model.PlatformData

fun PlatformDataDto.toDomain() : PlatformData {
    return PlatformData(
        email = email
    )
}