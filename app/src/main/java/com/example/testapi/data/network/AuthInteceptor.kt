package com.example.testapi.data.network

import android.util.Log
import com.example.testapi.domain.repository.LocalDataSourceRepository
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val localRepository: LocalDataSourceRepository
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val token = localRepository.getToken()
        Log.d("TOKEN_DEBUG", "Token in Interceptor = $token")

        val newRequest = request.newBuilder()
        token?.let {
            newRequest.addHeader("Authorization", "Bearer $it")
            Log.d("Bearer", "Token = $it")
        }

        return chain.proceed(newRequest.build())
    }
}