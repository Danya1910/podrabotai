package com.example.testapi.domain.repository

import com.example.testapi.domain.model.GetProfileResponse

interface UserRepository {

    suspend fun getProfile() : GetProfileResponse

}