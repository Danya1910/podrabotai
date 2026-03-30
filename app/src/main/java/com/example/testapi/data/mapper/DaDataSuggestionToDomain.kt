package com.example.testapi.data.mapper

import com.example.testapi.data.dto.response.DaDataSuggestionDto
import com.example.testapi.domain.model.DaDataSuggestions

fun DaDataSuggestionDto.toDomain() : DaDataSuggestions {
    return DaDataSuggestions(
        value = value,
        data = data.toDomain()
    )
}