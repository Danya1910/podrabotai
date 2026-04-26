package com.example.testapi.domain.repository

import android.content.Context

interface LocationAddressRepository {

    suspend fun getCity(
        context: Context,
        lat: Double,
        lng: Double,
    ) : String

}