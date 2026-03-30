package com.example.testapi.presentation.states

import com.example.testapi.domain.model.GetCitiesResponse

data class GetCitiesState(
    val isLoading: Boolean = false,
    val isSuccessful: Boolean = false,
    val cities: List<GetCitiesResponse>? = null,
    val error: String? = null
)