package com.example.testapi.domain.repository

import com.example.testapi.domain.model.DaDataRequest
import com.example.testapi.domain.model.DaDataResponse

interface DaDataRepository {
    suspend fun getSuggestions(query: String, count: Int = 5): List<DaDataResponse>
}