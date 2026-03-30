package com.example.testapi.domain.usecase

import com.example.testapi.domain.repository.LocalDataSourceRepository
import javax.inject.Inject

class ClearTokenUseCase @Inject constructor(
    private val repository: LocalDataSourceRepository
) {
    operator fun invoke() {
        repository.clearToken()
    }
}