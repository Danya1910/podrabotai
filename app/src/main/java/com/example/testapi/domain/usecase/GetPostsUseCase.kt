package com.example.testapi.domain.usecase

import com.example.testapi.domain.model.LoginResponse
import com.example.testapi.domain.repository.AuthRepository

class GetPostsUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(): List<LoginResponse> = repository.getPosts()
}