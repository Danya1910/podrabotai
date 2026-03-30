package com.example.testapi.domain.usecase

import com.example.testapi.domain.model.GetProfileResponse
import com.example.testapi.domain.repository.UserRepository
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val repository: UserRepository
) {

    suspend operator fun invoke() : GetProfileResponse {
        return repository.getProfile()
    }

}