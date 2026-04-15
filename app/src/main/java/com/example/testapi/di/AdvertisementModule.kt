package com.example.testapi.di

import com.example.testapi.domain.repository.AdvertisementRepository
import com.example.testapi.domain.usecase.AddToFavoriteUseCase
import com.example.testapi.domain.usecase.AddToHistoryUseCase
import com.example.testapi.domain.usecase.CreateAdvertisementUseCase
import com.example.testapi.domain.usecase.DeleteAdvertisementUseCase
import com.example.testapi.domain.usecase.DeleteFromFavoriteUseCase
import com.example.testapi.domain.usecase.GetAdvertisementsUseCase
import com.example.testapi.domain.usecase.GetCitiesUseCase
import com.example.testapi.domain.usecase.GetDetailedAdvertisementUseCase
import com.example.testapi.domain.usecase.GetFavoritesUseCase
import com.example.testapi.domain.usecase.GetHistoryUseCase
import com.example.testapi.domain.usecase.GetMyAdvertisementsUseCase
import com.example.testapi.domain.usecase.UpdateAdvertisementUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object AdvertisementModule {


    @Provides
    fun provideGetAdvertisementsUseCase(
        repository: AdvertisementRepository
    ) = GetAdvertisementsUseCase(repository)

    @Provides
    fun provideGetDetailedAdvertisementUseCase(
        repository: AdvertisementRepository
    ) = GetDetailedAdvertisementUseCase(repository)

    @Provides
    fun provideCreateAdvertisementUseCase(
        repository: AdvertisementRepository
    ) = CreateAdvertisementUseCase(repository)

    @Provides
    fun provideUpdateAdvertisementUseCase(
        repository: AdvertisementRepository
    ) = UpdateAdvertisementUseCase(repository)

    @Provides
    fun provideDeleteAdvertisementUseCase(
        repository: AdvertisementRepository
    ) = DeleteAdvertisementUseCase(repository)

    @Provides
    fun provideGetMyAdvertisementsUseCase(
        repository: AdvertisementRepository
    ) = GetMyAdvertisementsUseCase(repository)

    @Provides
    fun provideAddToFavoriteUseCase(
        repository: AdvertisementRepository
    ) = AddToFavoriteUseCase(repository)

    @Provides
    fun provideDeleteFromFavoriteUseCase(
        repository: AdvertisementRepository
    ) = DeleteFromFavoriteUseCase(repository)

    @Provides
    fun provideGetFavoritesUseCase(
        repository: AdvertisementRepository
    ) = GetFavoritesUseCase(repository)

    @Provides
    fun provideAddToHistoryUseCase(
        repository: AdvertisementRepository
    ) = AddToHistoryUseCase(repository)

    @Provides
    fun provideGetHistoryUseCase(
        repository: AdvertisementRepository
    ) = GetHistoryUseCase(repository)

    @Provides
    fun provideGetCitiesUseCase(
        repository: AdvertisementRepository
    ) = GetCitiesUseCase(repository)

}