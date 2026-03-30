package com.example.testapi.data.dto.request

data class RecoveryPasswordRequestDto(
    val temporaryId: Int,
    val code: Int,
    val password: String
)
