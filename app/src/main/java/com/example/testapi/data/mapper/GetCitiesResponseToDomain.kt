package com.example.testapi.data.mapper

import com.example.testapi.data.dto.response.GetCitiesResponseDto
import com.example.testapi.domain.model.GetCitiesResponse

fun GetCitiesResponseDto.toDomain() : List<GetCitiesResponse> {
    return data.map {(id, name) ->
        GetCitiesResponse(
            id = id.toInt(),
            city = name
        )
    }
}