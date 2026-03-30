package com.example.testapi.data.mapper

import com.example.testapi.data.dto.request.RegisterRequestDto
import com.example.testapi.domain.model.RegisterRequest

fun RegisterRequest.toCreateDto() : RegisterRequestDto {
    return RegisterRequestDto(
        username = userName,
        email = email,
        password = password,
        user_role = role,
    )
}