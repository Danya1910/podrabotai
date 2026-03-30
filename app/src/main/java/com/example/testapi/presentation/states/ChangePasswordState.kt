package com.example.testapi.presentation.states

import com.example.testapi.domain.model.ChangePasswordResponse

data class ChangePasswordState(
    val isLoading: Boolean = false,
    val isSuccessful: Boolean = false,
    val changePassword: ChangePasswordResponse? = null,
    val error: String? = null
)
