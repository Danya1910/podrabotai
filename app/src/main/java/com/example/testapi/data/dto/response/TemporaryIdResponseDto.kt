package com.example.testapi.data.dto.response

import com.google.gson.annotations.SerializedName

data class TemporaryIdResponseDto(
    @SerializedName("temporary_id")
    val temporaryId: Int,
)
