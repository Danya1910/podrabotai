package com.example.testapi.domain.usecase

import com.example.testapi.domain.model.DetailedAdvertisement
import com.example.testapi.domain.repository.AdvertisementRepository
import com.example.testapi.domain.repository.AuthRepository
import javax.inject.Inject

class GetDetailedAdvertisementUseCase(
    private val repository: AdvertisementRepository
) {

    suspend operator fun invoke(
        jobId: Int
    ): DetailedAdvertisement = repository.getDetailedAdvertisement(jobId = jobId)
}