package com.example.testapi.presentation.states

import com.example.testapi.domain.model.RecoveryCodeResponse

data class RecoveryCodeState(
    val isSuccessful: Boolean = false,
    val isLoading: Boolean = false,
    val recoveryCode: RecoveryCodeResponse? = null,
    val statusCode: Int = 0,
    val message: String? = "",
    val error: String? = ""
)