package com.example.testapi.data.mapper

import com.example.testapi.data.dto.response.BigDataCloudResponseDto
import com.example.testapi.domain.model.BigDataCloudResponse

fun BigDataCloudResponseDto.toDomain() : BigDataCloudResponse {
    return BigDataCloudResponse(
        city = city
    )
}