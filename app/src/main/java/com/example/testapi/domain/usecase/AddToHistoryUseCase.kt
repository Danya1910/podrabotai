package com.example.testapi.domain.usecase

import com.example.testapi.domain.model.AddToHistoryRequest
import com.example.testapi.domain.model.AddToHistoryResponse
import com.example.testapi.domain.repository.AdvertisementRepository
import javax.inject.Inject

class AddToHistoryUseCase @Inject constructor(
    private val repository: AdvertisementRepository
) {

    suspend operator fun invoke(
        jobId: Int
    ) : AddToHistoryResponse {
        val request = AddToHistoryRequest(
            jobId = jobId
        )
        return repository.addToHistory(request = request)
    }
}