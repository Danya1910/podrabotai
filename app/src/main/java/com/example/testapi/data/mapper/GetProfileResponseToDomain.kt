package com.example.testapi.data.mapper

import com.example.testapi.data.dto.response.GetProfileResponseDto
import com.example.testapi.domain.model.GetProfileResponse

fun GetProfileResponseDto.toDomain() : GetProfileResponse {
    return GetProfileResponse(
        banned = banned,
        createdAt = created_at,
        id = id,
        isAdmin = is_admin,
        lastLoginAt = last_login_at,
        phone = phone,
        photo = photo,
        platformData = platform_data.toDomain(),
        userName = user_name,
        userRole = user_role
    )
}