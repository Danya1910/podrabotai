package com.example.testapi.data.dto.request

data class AdvertisementFilterDto(
    val car: Boolean? = null,
    val salary: Int? = null,
    val age: Int? = null,
    val xp: Int? = null,
    val address: String? = null,
    val date: String? = null
)
