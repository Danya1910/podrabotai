package com.example.testapi.data.mapper

import com.example.testapi.data.dto.response.GetHistoryResponseDto
import com.example.testapi.domain.model.Advertisement

fun GetHistoryResponseDto.toDomain() : List<Advertisement> {
    return data.map { it.toDomain() }
}