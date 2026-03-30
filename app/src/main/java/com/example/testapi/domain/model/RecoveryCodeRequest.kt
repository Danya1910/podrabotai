package com.example.testapi.domain.model

data class RecoveryCodeRequest(
    val temporaryId: Int,
    val code: Int
)
