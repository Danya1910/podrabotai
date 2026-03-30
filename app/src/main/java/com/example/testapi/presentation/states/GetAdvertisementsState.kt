package com.example.testapi.presentation.states

import com.example.testapi.domain.model.Advertisement

data class GetAdvertisementsState(
    val isLoading: Boolean = false,
    val isSuccessful: Boolean = false,
    val getAdvertisements: List<Advertisement> = emptyList(),
    val error: String? = null
)
