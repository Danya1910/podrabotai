package com.example.testapi.data.dto.response

import com.example.testapi.domain.model.Advertisement

data class GetHistoryResponseDto(
    val data: List<AdvertisementResponseDto>
)
