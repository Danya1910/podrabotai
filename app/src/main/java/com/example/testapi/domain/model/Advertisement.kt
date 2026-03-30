package com.example.testapi.domain.model

data class Advertisement(
    val address: String,
    val car: Boolean,
    val city: String?,
    val id: Int,
    val isFavorite: Boolean,
    val isUrgent: Boolean,
    val salary: Int,
    val timeEnd: String,
    val timeStart: String,
    val title: String,
    val user: UserAdvertisementData,
)

data class UserAdvertisementData(
    val id: Int,
    val phone: String?,
    val photo: String?,
    val userName: String
)
