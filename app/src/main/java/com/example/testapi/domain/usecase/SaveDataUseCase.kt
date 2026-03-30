package com.example.testapi.domain.usecase

import com.example.testapi.domain.repository.LocalDataSourceRepository
import javax.inject.Inject

class SaveDataUseCase @Inject constructor(
    private val repository: LocalDataSourceRepository
) {
    operator fun invoke(
        email: String,
        password: String
    ) {
        return repository.saveData(email = email, password = password)
    }
}