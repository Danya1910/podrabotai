package com.example.testapi.domain.model

data class CreateAdvertisementRequest(
    val title: String,
    val wantedJob: String,
    val description: String,
    val salary: Int,
    val date: String,
    val timeStart: String,
    val timeEnd: String,
    val address: String,
    val city: String,
    val cityId: Int,
    val xp: Int,
    val age: Int,
    val isUrgent: Boolean,
    val car: Boolean
)
