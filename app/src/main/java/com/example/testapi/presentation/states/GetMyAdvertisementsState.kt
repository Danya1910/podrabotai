package com.example.testapi.presentation.states

import com.example.testapi.domain.model.Advertisement

data class GetMyAdvertisementsState(
    val isLoading: Boolean = false,
    val isSuccessful: Boolean = false,
    val myAdvertisements: List<Advertisement> = emptyList(),
    val error: String? = null
)
