package com.example.testapi.domain.usecase

import com.example.testapi.domain.model.GetChatsResponse
import com.example.testapi.domain.repository.ChatRepository
import javax.inject.Inject

class GetChatsUseCase @Inject constructor(
    private val repository: ChatRepository
) {

    suspend operator fun invoke(): List<GetChatsResponse> {
        return repository.getChats()
    }

}