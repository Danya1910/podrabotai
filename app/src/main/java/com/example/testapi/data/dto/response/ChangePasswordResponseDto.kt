package com.example.testapi.data.dto.response

data class ChangePasswordResponseDto(
    val message: String,
    val access_token: String,
    val role: String
)
