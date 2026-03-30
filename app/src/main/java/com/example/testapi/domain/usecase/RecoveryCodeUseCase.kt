package com.example.testapi.domain.usecase

import com.example.testapi.domain.model.RecoveryCodeRequest
import com.example.testapi.domain.model.RecoveryCodeResponse
import com.example.testapi.domain.repository.AuthRepository
import javax.inject.Inject

class RecoveryCodeUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(
        temporaryId: Int,
        code: Int
    ) : RecoveryCodeResponse {
        val request = RecoveryCodeRequest(
            temporaryId = temporaryId,
            code = code
        )
        return repository.recoveryCode(request)
    }
}