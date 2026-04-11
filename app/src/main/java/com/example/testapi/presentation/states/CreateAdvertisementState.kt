package com.example.testapi.presentation.states

import com.example.testapi.domain.model.CreateAdvertisementResponse

data class CreateAdvertisementState(
    val isLoading: Boolean = false,
    val isSuccessful: Boolean = false,
    val createAd: CreateAdvertisementResponse? = null,
    val error: String? = null
)
