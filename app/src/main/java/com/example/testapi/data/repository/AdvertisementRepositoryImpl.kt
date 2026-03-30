package com.example.testapi.data.repository

import android.util.Log
import com.example.testapi.data.api.PostApi
import com.example.testapi.data.dto.request.AdvertisementFilterDto
import com.example.testapi.data.dto.request.CreateAdvertisementRequestDto
import com.example.testapi.data.dto.request.DeleteFromFavoriteRequestDto
import com.example.testapi.data.dto.response.CreateAdvertisementResponseDto
import com.example.testapi.data.mapper.toCreateDto
import com.example.testapi.data.mapper.toDomain
import com.example.testapi.data.mapper.toQueryMap
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
import com.example.testapi.domain.repository.AdvertisementRepository
import javax.inject.Inject

class AdvertisementRepositoryImpl @Inject constructor(
    private val api: PostApi
) : AdvertisementRepository {
    override suspend fun getAdvertisements(filter: AdvertisementFilter?): List<Advertisement> {
        Log.d("REPO_DEBUG", "getAdvertisements called")
        val queryMap = filter?.toCreateDto()?.toQueryMap() ?: emptyMap()
        val response = api.getAdvertisements(queryMap)
        return response.data.jobs.map { it.toDomain() }
    }

    override suspend fun getDetailedAdvertisement(jobId: Int): DetailedAdvertisement {
        Log.d("REPO_DEBUG", "getDetailedAdvertisement called")
        val response = api.getDetailedAdvertisement(jobId = jobId)
        return response.data.toDomain()
    }

    override suspend fun createAdvertisement(request: CreateAdvertisementRequest): CreateAdvertisementResponse {
        Log.d("REPO_DEBUG", "createAdvertisement called")
        return api.createAdvertisement(request = request.toCreateDto()).toDomain()
    }

    override suspend fun updateAdvertisement(request: UpdateAdvertisementRequest, jobId: Int) : UpdateAdvertisementResponse {
        Log.d("REPO_DEBUG", "updateAdvertisement called")
        return api.updateJob(jobId = jobId, request = request.toCreateDto()).toDomain()
    }

    override suspend fun deleteAdvertisement(jobId: Int) : DeleteAdvertisementResponse {
        return api.deleteAdvertisement(jobId = jobId).toDomain()
    }

    override suspend fun getMyAdvertisements() : List<Advertisement> {
        return api.getMyAdvertisements().data.jobs.map { it.toDomain() }
    }

    override suspend fun addToFavorite(request: AddToFavoriteRequest): AddToFavoriteResponse {
        Log.d("REPO_DEBUG", "AddToFavorite called")
        val response = api.addToFavorite(request = request.toCreateDto())
        return response.data.toDomain()
    }

    override suspend fun deleteFromFavorite(request: DeleteFromFavoriteRequest): DeleteFromFavoriteResponse {
        Log.d("REPO_DEBUG", "DeleteFromFavorite called, jobId=${request.jobId}")

        return try {
            // Преобразуем в DTO и отправляем запрос
            val requestDto = request.toCreateDto()
            Log.d("REPO_DEBUG", "DeleteFromFavorite request DTO: $requestDto")

            val responseDto = api.deleteFromFavorite(requestDto)
            Log.d("REPO_DEBUG", "DeleteFromFavorite raw response DTO: $responseDto")

            val domainResponse = responseDto.data.toDomain()
            Log.d("REPO_DEBUG", "DeleteFromFavorite mapped domain response: $domainResponse")

            domainResponse
        } catch (e: Exception) {
            Log.d("REPO_DEBUG", "DeleteFromFavorite API exception: ${e.message}")
            throw e
        }
    }

    override suspend fun getFavorites(): List<Advertisement> {
        val response = api.getFavorites()
        return response.data.map { it.toDomain() }
    }

    override suspend fun addToHistory(request: AddToHistoryRequest) : AddToHistoryResponse {
        Log.d("REPO_DEBUG", "AddToHistory called")
        return api.addToHistory(request = request.toCreateDto()).toDomain()
    }

    override suspend fun getHistory() : List<Advertisement> {
        Log.d("REPO_DEBUG", "GETHistory called")
        return api.getHistory().toDomain()
    }

    override suspend fun getCities(): List<GetCitiesResponse> {
        return api.getCities().toDomain()
    }
}