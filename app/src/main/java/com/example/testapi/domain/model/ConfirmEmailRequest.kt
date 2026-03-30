package com.example.testapi.domain.model

data class ConfirmEmailRequest(
    val temporaryId: Int,
    val code: Int
)
