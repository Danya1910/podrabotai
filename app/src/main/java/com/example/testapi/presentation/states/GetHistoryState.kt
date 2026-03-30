package com.example.testapi.presentation.states

import com.example.testapi.domain.model.Advertisement

data class GetHistoryState(
    val isLoading: Boolean = false,
    val isSuccessful: Boolean = false,
    val getHistory: List<Advertisement> = emptyList(),
    val error: String? = null
)
