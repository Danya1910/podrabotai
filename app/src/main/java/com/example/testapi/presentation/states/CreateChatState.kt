package com.example.testapi.presentation.states

import com.example.testapi.domain.model.CreateChatResponse

data class CreateChatState(
    val isLoading: Boolean = false,
    val isSuccessful: Boolean = false,
    val createChat: CreateChatResponse? = null,
    val error: String? = null
)
