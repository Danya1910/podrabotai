package com.example.testapi.domain.usecase

import com.example.testapi.domain.model.CreateChatRequest
import com.example.testapi.domain.model.CreateChatResponse
import com.example.testapi.domain.repository.ChatRepository
import javax.inject.Inject

class CreateChatUseCase @Inject constructor(
    private val repository: ChatRepository
) {

    suspend operator fun invoke(
        penpalId: Int,
        jobId: Int
    ) : CreateChatResponse {
        val request = CreateChatRequest(
            penpalId = penpalId,
            jobId = jobId
        )
        return repository.createChat(request = request)
    }
}