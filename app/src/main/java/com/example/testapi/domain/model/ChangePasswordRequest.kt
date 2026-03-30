package com.example.testapi.domain.model

data class ChangePasswordRequest(
    val old_password: String,
    val new_password: String
)
