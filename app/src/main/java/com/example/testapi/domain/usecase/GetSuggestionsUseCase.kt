package com.example.testapi.domain.usecase

import com.example.testapi.domain.model.DaDataRequest
import com.example.testapi.domain.model.DaDataResponse
import com.example.testapi.domain.repository.DaDataRepository
import javax.inject.Inject

class GetSuggestionsUseCase @Inject constructor(
    private val repository: DaDataRepository
){

    suspend operator fun invoke(
        query: String
    ) : List<DaDataResponse> {
        return repository.getSuggestions(query = query)
    }

}