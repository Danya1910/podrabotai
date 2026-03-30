package com.example.testapi.domain.model

data class LoginResponse(
    val message: String,
    val accessToken: String,
    val role: String
)