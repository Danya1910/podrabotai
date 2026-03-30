package com.example.testapi.presentation.states

import com.example.testapi.domain.model.SendMessageResponse

data class SendMessageState(
    val isLoading: Boolean = false,
    val isSuccessful: Boolean = false,
    val sendMessage: SendMessageResponse? = null,
    val error: String? = null
)
