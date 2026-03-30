package com.example.testapi.data.mapper

import com.example.testapi.data.dto.request.ChangePasswordRequestDto
import com.example.testapi.domain.model.ChangePasswordRequest

fun ChangePasswordRequest.toCreateDto() : ChangePasswordRequestDto {
    return ChangePasswordRequestDto(
        old_password = old_password,
        new_password = new_password
    )
}