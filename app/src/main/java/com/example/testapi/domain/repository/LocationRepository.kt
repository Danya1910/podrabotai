package com.example.testapi.domain.repository

import com.example.testapi.domain.model.LocationData

interface LocationRepository {

    suspend fun getLocation() : LocationData?

}