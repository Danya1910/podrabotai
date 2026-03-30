package com.example.testapi.domain.usecase

import com.example.testapi.domain.model.ConfirmEmailRequest
import com.example.testapi.domain.model.ConfirmEmailResponse
import com.example.testapi.domain.repository.AuthRepository
import javax.inject.Inject

class ConfirmEmailUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(
        temporaryId: Int,
        code: Int
    ) : ConfirmEmailResponse {
        val request = ConfirmEmailRequest(
            temporaryId = temporaryId,
            code = code
        )
        return repository.confirmEmail(request = request)
    }
}