package com.example.testapi.data.dto.response

data class ChatJobDto(
    val address: String,
    val car: Boolean,
    val is_urgent: Boolean,
    val salary: Int,
    val time_end: String,
    val time_start: String,
    val title: String
)
