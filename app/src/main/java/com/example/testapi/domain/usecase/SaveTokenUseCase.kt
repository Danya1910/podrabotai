package com.example.testapi.domain.usecase

import com.example.testapi.domain.repository.LocalDataSourceRepository
import javax.inject.Inject

class SaveTokenUseCase @Inject constructor(
    private val repository: LocalDataSourceRepository
) {
    operator fun invoke(token : String) {
        repository.saveToken(token = token)
    }
}