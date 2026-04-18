package com.example.testapi.domain.usecase

import com.example.testapi.domain.model.BigDataCloudResponse
import com.example.testapi.domain.repository.BigDataCloudRepository
import javax.inject.Inject

class GetPositionUseCase @Inject constructor(
    private val repository: BigDataCloudRepository
) {

    suspend operator fun invoke(
        lat: Double,
        lng: Double
    ) : BigDataCloudResponse {
        return repository.getPosition(lat = lat, lng = lng)
    }

}