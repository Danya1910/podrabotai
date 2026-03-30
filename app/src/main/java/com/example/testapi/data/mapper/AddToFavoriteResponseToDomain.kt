package com.example.testapi.data.mapper

import com.example.testapi.data.dto.response.AddToFavoriteResponseDto
import com.example.testapi.domain.model.AddToFavoriteResponse

fun AddToFavoriteResponseDto.toDomain() : AddToFavoriteResponse {
    return AddToFavoriteResponse(
        createdAt = created_at,
        id = id,
        jobId =  job_id,
        userId = user_id
    )
}