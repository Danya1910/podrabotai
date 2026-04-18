package com.example.testapi.data.api

import com.example.testapi.data.dto.response.BigDataCloudResponseDto
import org.intellij.lang.annotations.Language
import retrofit2.http.GET
import retrofit2.http.Query

interface BigDataCloudApi {

    @GET("data/reverse-geocode-client")
    suspend fun getCity(
        @Query("latitude") lat: Double,
        @Query("longitude") lng: Double,
        @Query("localityLanguage") language: String = "ru"
    ) : BigDataCloudResponseDto

}