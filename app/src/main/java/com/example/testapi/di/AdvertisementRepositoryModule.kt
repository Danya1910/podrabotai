package com.example.testapi.di

import com.example.testapi.data.repository.AdvertisementRepositoryImpl
import com.example.testapi.domain.repository.AdvertisementRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AdvertisementRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAdvertisementRepository(
        impl: AdvertisementRepositoryImpl
    ): AdvertisementRepository
}
