package com.example.testapi.data.dto.request

data class UpdateAdvertisementRequestDto(
    val title: String?,
    val wanted_job: String?,
    val description: String?,
    val salary: Int?,
    val date: String?,
    val time_start: String?,
    val time_end: String?,
    val address: String?,
    val city_id: Int?,
    val city: String?,
    val xp: Int?,
    val age: Int?,
    val is_urgent: Boolean?,
    val car: Boolean?
)
