package com.example.testapi.di

import com.example.testapi.data.repository.ChatRepositoryImpl
import com.example.testapi.data.repository.UserRepositoryImpl
import com.example.testapi.domain.repository.ChatRepository
import com.example.testapi.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class UserRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        impl: UserRepositoryImpl
    ): UserRepository

}

