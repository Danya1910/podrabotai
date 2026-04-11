package com.example.testapi.presentation.states

import com.example.testapi.domain.model.ChatMessages

data class GetChatHistoryState(
    val isLoading: Boolean = false,
    val isSuccessful: Boolean = false,
    val chatHistory: ChatMessages? = null,
    val error: String? = null
)
