package com.example.testapi.data.mapper

import com.example.testapi.data.dto.response.DaDataDataDto
import com.example.testapi.domain.model.DaDataData

fun DaDataDataDto.toDomain() : DaDataData {
    return DaDataData(
        city = city,
        settlement = settlement,
        streetWithType = street_with_type,
        settlementWithType = settlement_with_type,
        unrestrictedValue = unrestricted_value,
    )
}