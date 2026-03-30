package com.example.testapi.domain.model

data class ChangePasswordResponse (
    val message: String,
    val access_token: String,
    val role: String
)