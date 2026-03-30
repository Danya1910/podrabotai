package com.example.testapi.presentation.states

import com.example.testapi.domain.model.AddToFavoriteResponse

data class AddToFavoriteState(
    val isLoading: Boolean = false,
    val isSuccessful: Boolean = false,
    val addToFavorite: AddToFavoriteResponse? = null,
    val error: String? = null
)
