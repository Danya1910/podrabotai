package com.example.testapi.data.dto.response

data class AdvertisementResponseDto (
    val address: String,
    val car: Boolean,
    val city: String?,
    val id: Int,
    val is_favorite: Boolean,
    val is_urgent: Boolean,
    val salary: Int,
    val title: String,
    val time_end: String,
    val time_start: String,
    val user: UserAdvertisementDataDto,
)

