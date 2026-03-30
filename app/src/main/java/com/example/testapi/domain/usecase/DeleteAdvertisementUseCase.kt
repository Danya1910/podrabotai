package com.example.testapi.domain.usecase

import com.example.testapi.domain.model.DeleteAdvertisementResponse
import com.example.testapi.domain.repository.AdvertisementRepository
import javax.inject.Inject

class DeleteAdvertisementUseCase @Inject constructor(
    private val repository: AdvertisementRepository
) {
    suspend operator fun invoke(jobId: Int) : DeleteAdvertisementResponse {
        return repository.deleteAdvertisement(jobId)
    }
}