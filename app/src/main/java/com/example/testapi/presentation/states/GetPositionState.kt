package com.example.testapi.presentation.states

import com.example.testapi.domain.model.BigDataCloudResponse

data class GetPositionState(
    val isLoading: Boolean = false,
    val isSuccessful: Boolean = false,
    val position: BigDataCloudResponse? = null,
    val error: String? = null,
)
