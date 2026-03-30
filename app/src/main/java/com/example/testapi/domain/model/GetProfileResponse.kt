package com.example.testapi.domain.model

data class GetProfileResponse(
    val banned: Boolean,
    val createdAt: String,
    val id: Int,
    val isAdmin: Boolean,
    val lastLoginAt: String?,
    val phone: String?,
    val photo: String?,
    val platformData: PlatformData,
    val userName: String,
    val userRole: String
)
