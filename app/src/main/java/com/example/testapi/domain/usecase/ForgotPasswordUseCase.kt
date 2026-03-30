package com.example.testapi.domain.usecase

import com.example.testapi.domain.model.ForgotPasswordRequest
import com.example.testapi.domain.model.TemporaryIdResponse
import com.example.testapi.domain.repository.AuthRepository
import javax.inject.Inject

class ForgotPasswordUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(
        email: String
    ) : TemporaryIdResponse {
        val request = ForgotPasswordRequest(
            email = email
        )
        return repository.forgotPassword(request = request)
    }
}

