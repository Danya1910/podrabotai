package com.example.testapi.domain.usecase

import com.example.testapi.domain.model.Advertisement
import com.example.testapi.domain.repository.AdvertisementRepository
import javax.inject.Inject

class GetHistoryUseCase @Inject constructor(
    private val repository: AdvertisementRepository
) {
    suspend operator fun invoke() : List<Advertisement> {
        return repository.getHistory()
    }
}