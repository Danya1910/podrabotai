package com.example.testapi.di

import com.example.testapi.data.api.BigDataCloudApi
import com.example.testapi.domain.repository.BigDataCloudRepository
import com.example.testapi.domain.repository.LocationRepository
import com.example.testapi.domain.usecase.GetLocationUseCase
import com.example.testapi.domain.usecase.GetPositionUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object BigDataCloudModule {

    @Provides
    fun provideGetPositionUseCase(
        repository: BigDataCloudRepository
    ) = GetPositionUseCase(repository = repository)

    @Provides
    @Singleton
    @Named("bigdatacloud")
    fun provideBigDataCloudRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.bigdatacloud.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideBigDataCloudApi(
        @Named("bigdatacloud") retrofit: Retrofit
    ): BigDataCloudApi {
        return retrofit.create(BigDataCloudApi::class.java)
    }

}