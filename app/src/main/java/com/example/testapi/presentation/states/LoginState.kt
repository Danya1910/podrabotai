package com.example.testapi.presentation.states

import com.example.testapi.domain.model.LoginResponse


data class LoginState(
    val isLoading: Boolean = false,
    val isSuccessful: Boolean = false,
    val login: LoginResponse? = null,
    val error: String? = null,
)
