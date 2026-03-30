package com.example.testapi.presentation.states

import com.example.testapi.domain.model.TemporaryIdResponse

data class ForgotPasswordState(
    val isLoading: Boolean = false,
    val isSuccessful: Boolean = false,
    val forgotPassword: TemporaryIdResponse? = null,
    val error: String? = null
)
