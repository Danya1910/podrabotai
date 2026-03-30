package com.example.testapi.domain.usecase

import com.example.testapi.domain.model.LoginRequest
import com.example.testapi.domain.model.LoginResponse
import com.example.testapi.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String,
    ): LoginResponse {
        val request = LoginRequest(
            email = email,
            password = password
        )
        return repository.login(request)
    }
}