package com.example.testapi.data.local

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.testapi.domain.model.UserData

class TokenStore(context: Context) {
    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val prefs = EncryptedSharedPreferences.create(
        context,
        "token_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveToken(token: String) {
        prefs.edit().putString("token", token).apply()
    }

    fun getToken() : String? {
        return prefs.getString("token", null)
    }

    fun clearToken() {
        prefs.edit().remove("token").apply()
    }

    fun saveData(email: String, password: String) {
        prefs.edit().apply{
            putString("email", email)
            putString("password", password)
            apply()
        }
    }

    fun getData() : UserData {
        val email = prefs.getString("email", null)
        val password = prefs.getString("password", null)
        return UserData(email = email, password = password)
    }

}