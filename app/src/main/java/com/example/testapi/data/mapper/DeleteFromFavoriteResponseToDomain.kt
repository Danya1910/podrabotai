package com.example.testapi.data.mapper

import com.example.testapi.data.dto.response.DeleteFromFavoriteResponseDto
import com.example.testapi.domain.model.DeleteFromFavoriteResponse

fun DeleteFromFavoriteResponseDto.toDomain() : DeleteFromFavoriteResponse {
    return DeleteFromFavoriteResponse(
        deletedId = deleted_id,
        message = message ?: ""
    )
}