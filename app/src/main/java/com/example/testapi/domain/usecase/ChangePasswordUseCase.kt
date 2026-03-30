package com.example.testapi.domain.usecase

import com.example.testapi.domain.model.ChangePasswordRequest
import com.example.testapi.domain.model.ChangePasswordResponse
import com.example.testapi.domain.repository.AuthRepository
import javax.inject.Inject

class ChangePasswordUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(
        old_password: String,
        new_password: String
    ) : ChangePasswordResponse {
        val request = ChangePasswordRequest(
            old_password = old_password,
            new_password = new_password
        )
        return repository.changePassword(request = request)
    }
}