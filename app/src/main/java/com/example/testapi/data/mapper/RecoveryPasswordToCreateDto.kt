package com.example.testapi.data.mapper

import com.example.testapi.data.dto.request.RecoveryPasswordRequestDto
import com.example.testapi.domain.model.RecoveryPasswordRequest

fun RecoveryPasswordRequest.toCreateDto() : RecoveryPasswordRequestDto{
    return RecoveryPasswordRequestDto(
        temporaryId = temporaryId,
        code = code,
        password = password
    )
}