package com.example.testapi.di

import com.example.testapi.data.api.PostApi
import com.example.testapi.data.repository.AuthRepositoryImpl
import com.example.testapi.domain.repository.AuthRepository
import com.example.testapi.domain.usecase.ChangePasswordUseCase
import com.example.testapi.domain.usecase.ChangeRoleUseCase
import com.example.testapi.domain.usecase.ConfirmEmailUseCase
import com.example.testapi.domain.usecase.ForgotPasswordUseCase
import com.example.testapi.domain.usecase.LoginUseCase
import com.example.testapi.domain.usecase.LogoutUseCase
import com.example.testapi.domain.usecase.RecoveryCodeUseCase
import com.example.testapi.domain.usecase.RegisterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    fun provideLoginUseCase(repository: AuthRepository) =
        LoginUseCase(repository)

    @Provides
    fun provideRegisterUseCase(repository: AuthRepository) =
        RegisterUseCase(repository)

    @Provides
    fun provideConfirmEmailUseCase(repository: AuthRepository) =
        ConfirmEmailUseCase(repository)

    @Provides
    fun provideRecoveryCodeUseCase(repository: AuthRepository) =
        RecoveryCodeUseCase(repository)

    @Provides
    fun provideChangePasswordUseCase(repository: AuthRepository) =
        ChangePasswordUseCase(repository)

    @Provides
    fun provideForgotPasswordUseCase(repository: AuthRepository) =
        ForgotPasswordUseCase(repository)

    @Provides
    fun changeRoleUseCase(repository: AuthRepository) =
        ChangeRoleUseCase(repository)

    @Provides
    fun logoutUseCase(repository: AuthRepository) =
        LogoutUseCase(repository)
}