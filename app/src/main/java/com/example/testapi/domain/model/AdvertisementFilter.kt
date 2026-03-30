package com.example.testapi.domain.model

data class AdvertisementFilter(
    val car: Boolean? = null,
    val salary: Int? = null,
    val age: Int? = null,
    val xp: Int? = null,
    val address: String? = null,
    val date: String? = null
)