package com.example.testapi.domain.usecase

import com.example.testapi.domain.model.SendMessageRequest
import com.example.testapi.domain.model.SendMessageResponse
import com.example.testapi.domain.repository.ChatRepository
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val repository: ChatRepository
) {

    suspend operator fun invoke(
        penpalId: Int,
        text: String
    ) : SendMessageResponse {
        val request = SendMessageRequest(
            text = text
        )
        return repository.sendMessage(penpalId = penpalId, request = request)
    }
}