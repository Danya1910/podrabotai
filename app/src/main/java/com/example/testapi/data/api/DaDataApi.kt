package com.example.testapi.data.api

import com.example.testapi.data.dto.request.DaDataRequestDto
import com.example.testapi.data.dto.response.DaDataResponseDto
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface DaDataApi {

    @POST("suggestions/api/4_1/rs/suggest/address")
    suspend fun getSuggestions(
        @Header("Authorization") token: String,
        @Body request: DaDataRequestDto
    ) : DaDataResponseDto

}