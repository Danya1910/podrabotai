package com.example.testapi.data.local

import android.content.Context
import com.example.testapi.domain.model.UserData

class LocalDataSource(context: Context) {

    private val tokenStore = TokenStore(context)

    fun saveToken(token: String) {
        tokenStore.saveToken(token)
    }

    fun getToken(): String? {
        return tokenStore.getToken()
    }

    fun clearToken() {
        tokenStore.clearToken()
    }

    fun saveData(email: String, password: String) {
        tokenStore.saveData(
            email = email,
            password = password
        )
    }

    fun getData() : UserData {
        return tokenStore.getData()
    }

}