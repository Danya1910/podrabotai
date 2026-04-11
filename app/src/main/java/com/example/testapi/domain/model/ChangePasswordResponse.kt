package com.example.testapi.domain.model

data class ChangePasswordResponse (
    val message: String,
    val accessToken: String,
    val role: String
)