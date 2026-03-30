package com.example.testapi.presentation.states

import com.example.testapi.domain.model.ConfirmEmailResponse

data class ConfirmEmailState(
    val isLoading: Boolean = false,
    val isSuccessful: Boolean = false,
    val confirmMail: ConfirmEmailResponse? = null,
    val error: String? = null
)
