package com.example.testapi.di

import com.example.testapi.domain.repository.AdvertisementRepository
import com.example.testapi.domain.repository.ChatRepository
import com.example.testapi.domain.usecase.AddToFavoriteUseCase
import com.example.testapi.domain.usecase.CreateAdvertisementUseCase
import com.example.testapi.domain.usecase.CreateChatUseCase
import com.example.testapi.domain.usecase.DeleteFromFavoriteUseCase
import com.example.testapi.domain.usecase.GetAdvertisementsUseCase
import com.example.testapi.domain.usecase.GetChatHistoryUseCase
import com.example.testapi.domain.usecase.GetChatsUseCase
import com.example.testapi.domain.usecase.GetDetailedAdvertisementUseCase
import com.example.testapi.domain.usecase.SendMessageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ChatModule {


    @Provides
    fun provideGetChatsUseCase(
        repository: ChatRepository
    ) = GetChatsUseCase(repository)

    @Provides
    fun provideGetChatHistory(
        repository: ChatRepository
    ) = GetChatHistoryUseCase(repository)

    @Provides
    fun provideCreateChat(
        repository: ChatRepository
    ) = CreateChatUseCase(repository)

    @Provides
    fun provideSendMessage(
        repository: ChatRepository
    ) = SendMessageUseCase(repository)


}