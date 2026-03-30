package com.example.testapi.data.mapper

import com.example.testapi.data.dto.request.AddToFavoriteRequestDto
import com.example.testapi.domain.model.AddToFavoriteRequest

fun AddToFavoriteRequest.toCreateDto() : AddToFavoriteRequestDto {
    return AddToFavoriteRequestDto(
        job_id = jobId
    )
}