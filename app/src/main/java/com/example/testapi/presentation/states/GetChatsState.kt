package com.example.testapi.presentation.states

import com.example.testapi.domain.model.GetChatsResponse

data class GetChatsState(
    val isLoading: Boolean = false,
    val isSuccessful: Boolean = false,
    val getChats: List<GetChatsResponse>? = null,
    val error: String? = null
)
