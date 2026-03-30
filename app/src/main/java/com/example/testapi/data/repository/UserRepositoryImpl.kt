package com.example.testapi.data.repository

import android.util.Log
import com.example.testapi.data.api.PostApi
import com.example.testapi.data.mapper.toDomain
import com.example.testapi.domain.model.GetProfileResponse
import com.example.testapi.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: PostApi
) : UserRepository {

    override suspend fun getProfile() : GetProfileResponse {
        Log.d("REPO_DEBUG", "getProfile called")
        return api.getProfile().toDomain()
    }

}