package com.example.testapi.di

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import com.example.testapi.data.local.LocalDataSource
import com.example.testapi.data.repository.LocalDataSourceImpl
import com.example.testapi.domain.repository.LocalDataSourceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import javax.inject.Singleton
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalDataSourceModule {

    @Provides
    @Singleton
    fun provideLocalDataSource(@ApplicationContext context: Context): LocalDataSource {
        return LocalDataSource(context)
    }

    @Provides
    @Singleton
    fun provideLocalDataSourceRepository(localDataSource: LocalDataSource): LocalDataSourceRepository {
        return LocalDataSourceImpl(localDataSource)
    }
}