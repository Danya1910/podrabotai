package com.example.testapi.di

import com.example.testapi.data.api.DaDataApi
import com.example.testapi.data.repository.DaDataRepositoryImpl
import com.example.testapi.domain.repository.DaDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaDataModule {


    @Provides
    @Singleton
    @Named("dadata")
    fun provideDaDataRetrofit(): Retrofit {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://suggestions.dadata.ru/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideDaDataApi(@Named("dadata") retrofit: Retrofit): DaDataApi {
        return retrofit.create(DaDataApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDaDataRepository(api: DaDataApi): DaDataRepository {
        return DaDataRepositoryImpl(api)
    }
}