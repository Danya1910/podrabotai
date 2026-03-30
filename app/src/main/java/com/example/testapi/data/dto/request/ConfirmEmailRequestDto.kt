package com.example.testapi.data.dto.request

data class ConfirmEmailRequestDto(
    val temporary_id: Int,
    val code: Int
)
