package com.example.testapi.data.mapper

import com.example.testapi.data.dto.response.UpdateAdvertisementResponseDto
import com.example.testapi.domain.model.UpdateAdvertisementResponse

fun UpdateAdvertisementResponseDto.toDomain() : UpdateAdvertisementResponse{
    return UpdateAdvertisementResponse(
        data = data
    )
}