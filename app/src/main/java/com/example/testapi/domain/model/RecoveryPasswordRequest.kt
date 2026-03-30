package com.example.testapi.domain.model

data class RecoveryPasswordRequest (
    val temporaryId: Int,
    val code: Int,
    val password: String
)