package com.example.testapi.data.mapper

import com.example.testapi.data.dto.request.LoginRequestDto
import com.example.testapi.domain.model.LoginRequest


fun LoginRequest.toCreateDto(): LoginRequestDto {
    return LoginRequestDto(
        email = email,
        password = password,
    )
}