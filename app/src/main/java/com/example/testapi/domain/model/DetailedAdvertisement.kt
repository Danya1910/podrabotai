package com.example.testapi.domain.model

data class DetailedAdvertisement(
    val address: String,
    val age: Int,
    val car: Boolean,
    val city: String,
    val createdAt: String,
    val date: String,
    val description: String,
    val id: Int,
    val isFavorite: Boolean,
    val isUrgent: Boolean,
    val salary: Int,
    val status: Boolean,
    val timeEnd: String,
    val timeStart: String,
    val title: String,
    val user: UserAdvertisementData,
    val wantedJob: String,
    val xp: Int,
)

