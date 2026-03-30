package com.example.testapi.domain.usecase

import com.example.testapi.domain.model.GetCitiesResponse
import com.example.testapi.domain.repository.AdvertisementRepository
import javax.inject.Inject

class GetCitiesUseCase @Inject constructor(
    private val repository: AdvertisementRepository
) {
    suspend operator fun invoke(): List<GetCitiesResponse> {
        return repository.getCities()
    }
}