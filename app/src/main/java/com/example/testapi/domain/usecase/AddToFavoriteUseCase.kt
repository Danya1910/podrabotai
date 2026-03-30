package com.example.testapi.domain.usecase

import com.example.testapi.domain.model.AddToFavoriteRequest
import com.example.testapi.domain.model.AddToFavoriteResponse
import com.example.testapi.domain.repository.AdvertisementRepository
import javax.inject.Inject

class AddToFavoriteUseCase @Inject constructor(
    private val repository: AdvertisementRepository
){
    suspend operator fun invoke(
        jobId: Int
    ) : AddToFavoriteResponse {
        val request = AddToFavoriteRequest(
            jobId = jobId
        )
        return repository.addToFavorite(request)
    }
}