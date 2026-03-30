package com.example.testapi.data.dto.response

import com.example.testapi.domain.model.PlatformData

data class GetProfileResponseDto(
    val banned: Boolean,
    val created_at: String,
    val id: Int,
    val is_admin: Boolean,
    val last_login_at: String?,
    val phone: String?,
    val photo: String?,
    val platform_data: PlatformDataDto,
    val user_name: String,
    val user_role: String
)
