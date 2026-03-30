package com.example.testapi.data.mapper

import com.example.testapi.data.dto.response.TemporaryIdResponseDto
import com.example.testapi.domain.model.TemporaryIdResponse

fun TemporaryIdResponseDto.toDomain() : TemporaryIdResponse {
    return TemporaryIdResponse(
        temporaryId = temporaryId,
    )
}