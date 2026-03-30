package com.example.testapi.presentation.states

import com.example.testapi.domain.model.ChatMessages
import com.example.testapi.domain.usecase.GetChatHistoryUseCase

data class GetChatHistoryState(
    val isLoading: Boolean = false,
    val isSuccessful: Boolean = false,
    val getChatHistory: ChatMessages? = null,
    val error: String? = null
)
