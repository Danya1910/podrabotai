package com.example.testapi.di

import com.example.testapi.data.repository.LocationAddressRepositoryImpl
import com.example.testapi.domain.repository.LocationAddressRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class GeocoderModule {

    @Binds
    @Singleton
    abstract fun bindLocationAddressRepository(
        impl: LocationAddressRepositoryImpl
    ): LocationAddressRepository

}