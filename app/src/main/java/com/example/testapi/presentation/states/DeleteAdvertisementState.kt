package com.example.testapi.presentation.states

import com.example.testapi.domain.model.Advertisement
import com.example.testapi.domain.model.DeleteAdvertisementResponse

data class DeleteAdvertisementState(
    val isLoading: Boolean = false,
    val isSuccessful: Boolean = false,
    val deleteAd: DeleteAdvertisementResponse? = null,
    val error: String? = null
)
