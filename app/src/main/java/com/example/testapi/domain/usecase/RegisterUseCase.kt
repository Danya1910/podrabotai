package com.example.testapi.domain.usecase

import com.example.testapi.domain.model.RegisterRequest
import com.example.testapi.domain.model.TemporaryIdResponse
import com.example.testapi.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: AuthRepository
) {
   suspend operator fun invoke(
       userName: String,
       email: String,
       password: String,
       role: String
   ) : TemporaryIdResponse {
       val request = RegisterRequest(
           userName = userName,
           email = email,
           password = password,
           role = role
       )
        return repository.register(request = request)
   }
}