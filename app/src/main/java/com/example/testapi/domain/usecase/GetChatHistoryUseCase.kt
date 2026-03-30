package com.example.testapi.domain.usecase

import com.example.testapi.domain.model.ChatMessages
import com.example.testapi.domain.repository.ChatRepository
import javax.inject.Inject

class GetChatHistoryUseCase @Inject constructor(
    private val repository: ChatRepository
) {

    suspend operator fun invoke(
        penpalId: Int
    ) : ChatMessages {
        return repository.getChatHistory(penpalId = penpalId)
    }

}