package com.example.testapi.presentation.states

import com.example.testapi.domain.model.AddToHistoryResponse

data class AddToHistoryState(
    val isLoading: Boolean = false,
    val isSuccessful: Boolean = false,
    val addToHistory: AddToHistoryResponse? = null,
    val error: String? = null
)
