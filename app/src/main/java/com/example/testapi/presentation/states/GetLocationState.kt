package com.example.testapi.presentation.states

import com.example.testapi.domain.model.LocationData

data class GetLocationState(
    val isLoading: Boolean = false,
    val isSuccessful: Boolean = false,
    val location: LocationData? = null,
    val error: String? = null
)
