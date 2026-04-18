package com.example.testapi.domain.repository

import com.example.testapi.domain.model.BigDataCloudResponse

interface BigDataCloudRepository {

    suspend fun getPosition(lat: Double, lng: Double) : BigDataCloudResponse

}