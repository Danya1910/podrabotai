package com.example.testapi.data.mapper

import com.example.testapi.data.dto.request.RecoveryCodeRequestDto
import com.example.testapi.data.dto.request.RecoveryPasswordRequestDto
import com.example.testapi.domain.model.RecoveryCodeRequest

fun RecoveryCodeRequest.toCreateDto() : RecoveryCodeRequestDto {
    return RecoveryCodeRequestDto(
        temporary_id = temporaryId,
        code = code
    )
}