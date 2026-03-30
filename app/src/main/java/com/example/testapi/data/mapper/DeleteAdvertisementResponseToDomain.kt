package com.example.testapi.data.mapper

import com.example.testapi.data.dto.response.DeleteAdvertisementResponseDto
import com.example.testapi.domain.model.DeleteAdvertisementResponse

fun DeleteAdvertisementResponseDto.toDomain(): DeleteAdvertisementResponse {
    return DeleteAdvertisementResponse(
        data = data
    )
}