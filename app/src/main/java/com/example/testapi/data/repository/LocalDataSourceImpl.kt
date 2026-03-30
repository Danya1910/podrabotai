package com.example.testapi.data.repository

import com.example.testapi.data.local.LocalDataSource
import com.example.testapi.domain.model.UserData
import com.example.testapi.domain.repository.LocalDataSourceRepository

class LocalDataSourceImpl(
    private val localDataSource: LocalDataSource
) : LocalDataSourceRepository {

    override fun saveToken(token: String) {
        localDataSource.saveToken(token)
    }

    override fun getToken(): String? {
        return localDataSource.getToken()
    }

    override fun clearToken() {
        localDataSource.clearToken()
    }

    override fun saveData(email: String, password: String) {
        localDataSource.saveData(email = email, password = password)
    }

    override fun getData() : UserData {
        return localDataSource.getData()
    }
}