package com.example.testapi.presentation.states

import com.example.testapi.domain.model.DaDataResponse

data class GetSuggestionsState(
    val isLoading: Boolean = false,
    val isSuccessful: Boolean = false,
    val suggestions: List<DaDataResponse>? = null,
    val error: String? = null
)
