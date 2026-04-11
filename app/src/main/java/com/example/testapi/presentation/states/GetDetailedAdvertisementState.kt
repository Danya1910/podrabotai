package com.example.testapi.presentation.states

import com.example.testapi.domain.model.DetailedAdvertisement

data class GetDetailedAdvertisementState(
    val isLoading: Boolean = false,
    val isSuccessful: Boolean = false,
    val detailedAd: DetailedAdvertisement? = null,
    val error: String? = null
)
