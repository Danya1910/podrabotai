package com.example.testapi.presentation.states

import com.example.testapi.domain.model.Advertisement

data class GetFavoritesState(
    val isLoading: Boolean = false,
    val isSuccessful: Boolean = false,
    val favorites: List<Advertisement> = emptyList(),
    val error: String? = null
)
