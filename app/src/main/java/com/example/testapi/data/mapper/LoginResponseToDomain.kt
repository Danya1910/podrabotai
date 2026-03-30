package com.example.testapi.data.mapper

import com.example.testapi.data.dto.response.LoginResponseDto
import com.example.testapi.domain.model.LoginResponse

fun LoginResponseDto.toDomain() : LoginResponse {
    return LoginResponse(
        message = message,
        accessToken = accessToken,
        role = role
    )
}