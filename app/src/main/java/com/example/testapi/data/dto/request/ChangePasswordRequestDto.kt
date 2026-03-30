package com.example.testapi.data.dto.request

data class ChangePasswordRequestDto(
    val old_password: String,
    val new_password: String
)
