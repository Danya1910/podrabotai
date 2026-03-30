package com.example.testapi.domain.model

data class ConfirmEmailResponse (
    val message: String,
    val accessToken: String,
    val role: String
)