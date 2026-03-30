package com.example.testapi.domain.repository

import com.example.testapi.domain.model.UserData

interface LocalDataSourceRepository {
    fun saveToken(token: String)
    fun getToken() : String?
    fun clearToken()
    fun saveData(email: String, password: String)
    fun getData() : UserData?

}
