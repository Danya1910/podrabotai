package com.example.testapi.data.repository

import android.util.Log
import com.example.testapi.data.api.DaDataApi
import com.example.testapi.data.dto.request.DaDataRequestDto
import com.example.testapi.data.mapper.toDomain
import com.example.testapi.domain.model.DaDataResponse
import com.example.testapi.domain.repository.DaDataRepository
import javax.inject.Inject

class DaDataRepositoryImpl @Inject constructor(
    private val api: DaDataApi,
) : DaDataRepository {
    private  val token = "Token 211d8c431dd6807b5f7201155b55a46629d3cf76"
    override suspend fun getSuggestions(query: String, count: Int): List<DaDataResponse> {
        val request = DaDataRequestDto(query = query, count = count)
        val response = api.getSuggestions(request = request, token = token)

        val suggestionsList = response.suggestions.map { it.toDomain() }
        Log.d("REPO_DEBUG", "$suggestionsList")
        return listOf(DaDataResponse(suggestions = suggestionsList))
    }
}
