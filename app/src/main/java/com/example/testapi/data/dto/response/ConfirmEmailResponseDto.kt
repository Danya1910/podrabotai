package com.example.testapi.data.dto.response

import com.google.gson.annotations.SerializedName

data class ConfirmEmailResponseDto (
    val message: String,
    @SerializedName("access_token")
    val accessToken:String,
    val role: String
)