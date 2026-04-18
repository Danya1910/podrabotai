package com.example.testapi.data.repository

import com.example.testapi.data.api.BigDataCloudApi
import com.example.testapi.data.mapper.toDomain
import com.example.testapi.domain.model.BigDataCloudResponse
import com.example.testapi.domain.repository.BigDataCloudRepository
import javax.inject.Inject

class BigDataCloudRepositoryImpl @Inject constructor(
    private val api: BigDataCloudApi
) : BigDataCloudRepository {

    override suspend fun getPosition(lat: Double, lng: Double): BigDataCloudResponse {
        return api.getCity(lat = lat, lng = lng).toDomain()
    }

}