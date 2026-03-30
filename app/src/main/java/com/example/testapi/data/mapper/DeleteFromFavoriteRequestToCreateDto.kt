package com.example.testapi.data.mapper

import com.example.testapi.data.dto.request.DeleteFromFavoriteRequestDto
import com.example.testapi.domain.model.DeleteFromFavoriteRequest

fun DeleteFromFavoriteRequest.toCreateDto() : DeleteFromFavoriteRequestDto {
    return DeleteFromFavoriteRequestDto(
        job_id = jobId
    )
}