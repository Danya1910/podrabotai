package com.example.testapi.presentation.states

data class ChangeRoleState(
    val isLoading: Boolean = false,
    val isSuccessful: Boolean = false,
    val changeRole: String? = null,
    val error: String? = null
)
