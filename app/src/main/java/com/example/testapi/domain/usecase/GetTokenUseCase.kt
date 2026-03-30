package com.example.testapi.domain.usecase

import com.example.testapi.domain.repository.LocalDataSourceRepository
import javax.inject.Inject

class GetTokenUseCase @Inject constructor (
    private val repository: LocalDataSourceRepository
) {
    operator fun invoke(): String? {
        return repository.getToken()
    }
}