package com.example.testapi.domain.model

data class AddToFavoriteResponse(
    val createdAt: String?,
    val id: Int,
    val jobId: Int,
    val userId: Int
)
