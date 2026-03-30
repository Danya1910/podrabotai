package com.example.testapi.data.mapper

import com.example.testapi.data.dto.request.ForgotPasswordRequestDto
import com.example.testapi.domain.model.ForgotPasswordRequest

fun ForgotPasswordRequest.toCreateDto(): ForgotPasswordRequestDto {
    return ForgotPasswordRequestDto(
        email = email
    )
}