package com.example.testapi.data.dto.response

data class DetailedAdvertisementResponseDto(
    val address: String,
    val age: Int,
    val car: Boolean,
    val city: String,
    val created_at: String,
    val date: String,
    val description: String,
    val id: Int,
    val is_favorite: Boolean,
    val is_urgent: Boolean,
    val salary: Int,
    val status: Boolean,
    val time_end: String,
    val time_start: String,
    val title: String,
    val user: UserAdvertisementDataDto,
    val wanted_job: String,
    val xp: Int,
)


