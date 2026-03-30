package com.example.testapi.data.dto.response

data class CreateAdvertisementResponseDto (
    val job_id: Int,
    val title: String,
    val wanted_job: String,
    val salary: Int,
    val time_start: String,
    val time_end: String,
    val created_at: String,
    val address: String,
    val city: String,
    val car: Boolean,
    val message: String
)