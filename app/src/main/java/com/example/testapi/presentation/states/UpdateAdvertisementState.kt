package com.example.testapi.presentation.states

import com.example.testapi.domain.model.UpdateAdvertisementResponse

data class UpdateAdvertisementState(
    val isLoading: Boolean = false,
    val isSuccessful: Boolean = false,
    val update: UpdateAdvertisementResponse? = null,
    val error: String? = null
)
