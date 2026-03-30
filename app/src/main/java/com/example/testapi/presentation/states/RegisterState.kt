package com.example.testapi.presentation.states

import com.example.testapi.domain.model.TemporaryIdResponse

data class RegisterState(
    val isLoading: Boolean = false,
    val isSuccessful: Boolean = false,
    val register: TemporaryIdResponse? = null,
    val error: String? = null
)
