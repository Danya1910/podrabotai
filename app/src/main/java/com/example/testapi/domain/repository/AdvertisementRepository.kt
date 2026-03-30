package com.example.testapi.domain.repository

import com.example.testapi.domain.model.AddToFavoriteRequest
import com.example.testapi.domain.model.AddToFavoriteResponse
import com.example.testapi.domain.model.AddToHistoryRequest
import com.example.testapi.domain.model.AddToHistoryResponse
import com.example.testapi.domain.model.Advertisement
import com.example.testapi.domain.model.AdvertisementFilter
import com.example.testapi.domain.model.CreateAdvertisementRequest
import com.example.testapi.domain.model.CreateAdvertisementResponse
import com.example.testapi.domain.model.DeleteAdvertisementResponse
import com.example.testapi.domain.model.DeleteFromFavoriteRequest
import com.example.testapi.domain.model.DeleteFromFavoriteResponse
import com.example.testapi.domain.model.DetailedAdvertisement
import com.example.testapi.domain.model.GetCitiesResponse
import com.example.testapi.domain.model.UpdateAdvertisementRequest
import com.example.testapi.domain.model.UpdateAdvertisementResponse

interface AdvertisementRepository {

    suspend fun getAdvertisements(filter: AdvertisementFilter? = null) : List<Advertisement>

    suspend fun getDetailedAdvertisement(jobId: Int) : DetailedAdvertisement

    suspend fun createAdvertisement(request: CreateAdvertisementRequest) : CreateAdvertisementResponse

    suspend fun updateAdvertisement(request: UpdateAdvertisementRequest, jobId: Int) : UpdateAdvertisementResponse

    suspend fun deleteAdvertisement(jobId: Int) : DeleteAdvertisementResponse

    suspend fun getMyAdvertisements() : List<Advertisement>

    suspend fun addToFavorite(request: AddToFavoriteRequest) : AddToFavoriteResponse

    suspend fun deleteFromFavorite(request: DeleteFromFavoriteRequest) : DeleteFromFavoriteResponse

    suspend fun getFavorites() : List<Advertisement>

    suspend fun addToHistory(request: AddToHistoryRequest) : AddToHistoryResponse

    suspend fun getHistory() : List<Advertisement>

    suspend fun getCities() : List<GetCitiesResponse>
}