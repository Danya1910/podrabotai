package com.example.testapi.presentation.states

import com.example.testapi.domain.model.LoginResponse

data class RecoveryPasswordState (
    val isLoading: Boolean = false,
    val isSuccessful: Boolean = false,
    val recoveryPassword: LoginResponse? = null,
    val error: String? = null
)