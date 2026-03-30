package com.example.testapi.domain.usecase

import com.example.testapi.domain.repository.AuthRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(){
        return repository.logout()
    }
}