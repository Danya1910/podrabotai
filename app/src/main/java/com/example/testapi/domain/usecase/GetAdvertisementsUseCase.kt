package com.example.testapi.domain.usecase

import com.example.testapi.domain.model.Advertisement
import com.example.testapi.domain.model.AdvertisementFilter
import com.example.testapi.domain.repository.AdvertisementRepository

class GetAdvertisementsUseCase(
    private val repository: AdvertisementRepository
) {
    suspend operator fun invoke(
        filter: AdvertisementFilter? = null
    ): List<Advertisement> = repository.getAdvertisements(filter = filter)
}