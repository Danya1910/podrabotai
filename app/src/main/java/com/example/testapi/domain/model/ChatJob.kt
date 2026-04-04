package com.example.testapi.domain.model

data class ChatJob(
    val address: String,
    val car: Boolean,
    val isUrgent: Boolean,
    val salary: Int,
    val timeEnd: String,
    val timeStart: String,
    val title: String
)
