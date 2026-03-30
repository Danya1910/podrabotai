package com.example.testapi.data.dto.request

data class RegisterRequestDto(
    val username: String,
    val email: String,
    val password: String,
    val user_role: String
)
