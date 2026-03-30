package com.example.testapi.domain.model

data class CreateAdvertisementResponse(
    val jobId: Int?,
    val title: String?,
    val wantedJob: String?,
    val salary: Int?,
    val timeStart: String?,
    val timeEnd: String?,
    val createdAt: String?,
    val address: String?,
    val city: String?,
    val car: Boolean?,
    val message: String?
)
