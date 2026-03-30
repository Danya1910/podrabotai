package com.example.testapi.presentation.states

import com.example.testapi.domain.model.DeleteFromFavoriteResponse

data class DeleteFromFavoriteState(
    val isLoading: Boolean = false,
    val isSuccessful: Boolean = false,
    val deleteFromFavorite: DeleteFromFavoriteResponse? = null,
    val error: String? = null
)
