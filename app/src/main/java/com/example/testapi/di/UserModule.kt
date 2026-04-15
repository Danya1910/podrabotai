package com.example.testapi.di

import com.example.testapi.domain.repository.UserRepository
import com.example.testapi.domain.usecase.GetProfileUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object UserModule {

    @Provides
    fun provideGetProfileUseCase(
        repository: UserRepository
    ) = GetProfileUseCase(repository)

}