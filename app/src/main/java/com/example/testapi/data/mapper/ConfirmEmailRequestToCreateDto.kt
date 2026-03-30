package com.example.testapi.data.mapper

import com.example.testapi.data.dto.request.ConfirmEmailRequestDto
import com.example.testapi.domain.model.ConfirmEmailRequest

fun ConfirmEmailRequest.toCreateDto(): ConfirmEmailRequestDto {
    return ConfirmEmailRequestDto(
        temporary_id = temporaryId,
        code = code
    )
}