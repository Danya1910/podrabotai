package com.example.testapi.di

import com.example.testapi.data.repository.AuthRepositoryImpl
import com.example.testapi.data.repository.BigDataCloudRepositoryImpl
import com.example.testapi.domain.repository.AuthRepository
import com.example.testapi.domain.repository.BigDataCloudRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class BigDataCloudRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindBigDataCloudRepository(
        impl: BigDataCloudRepositoryImpl
    ): BigDataCloudRepository

}