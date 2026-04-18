package com.example.testapi.di

import com.example.testapi.domain.repository.LocationRepository
import com.example.testapi.domain.usecase.GetLocationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object LocationModule {

    @Provides
    fun provideGetLocationUseCase(
        repository: LocationRepository
    ) = GetLocationUseCase(repository = repository)

}