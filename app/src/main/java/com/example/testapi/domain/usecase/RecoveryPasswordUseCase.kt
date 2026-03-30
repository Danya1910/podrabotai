package com.example.testapi.domain.usecase

import com.example.testapi.domain.model.LoginResponse
import com.example.testapi.domain.model.RecoveryPasswordRequest
import com.example.testapi.domain.repository.AuthRepository
import javax.inject.Inject


class RecoveryPasswordUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(
        temporaryId: Int,
        code: Int,
        password: String
    ) : LoginResponse {
        val request = RecoveryPasswordRequest(
            temporaryId = temporaryId,
            code = code,
            password = password
        )
        return repository.recoveryPassword(request = request)
    }
}