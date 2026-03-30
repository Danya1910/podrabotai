package com.example.testapi.domain.usecase

import android.util.Log
import com.example.testapi.domain.model.DeleteFromFavoriteRequest
import com.example.testapi.domain.model.DeleteFromFavoriteResponse
import com.example.testapi.domain.repository.AdvertisementRepository
import javax.inject.Inject

class DeleteFromFavoriteUseCase @Inject constructor(
    private val repository: AdvertisementRepository
) {
    suspend operator fun invoke(jobId: Int): DeleteFromFavoriteResponse {
        Log.d("VM_DEBUG", "UseCase invoked for jobId = $jobId")
        return repository.deleteFromFavorite(DeleteFromFavoriteRequest(jobId = jobId))
    }
}