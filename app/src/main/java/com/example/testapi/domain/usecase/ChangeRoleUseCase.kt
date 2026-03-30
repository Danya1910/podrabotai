package com.example.testapi.domain.usecase

import com.example.testapi.domain.repository.AuthRepository
import javax.inject.Inject

class ChangeRoleUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke() : String {
        return repository.changeRole()
    }
}