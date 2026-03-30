package com.example.testapi.presentation.states

import com.example.testapi.domain.model.GetProfileResponse

data class GetProfileState(
    val isLoading: Boolean = false,
    val isSuccessful: Boolean = false,
    val getProfile: GetProfileResponse? = null,
    val error: String? = null
)
