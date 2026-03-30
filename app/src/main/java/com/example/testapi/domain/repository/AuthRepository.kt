package com.example.testapi.domain.repository

import com.example.testapi.domain.model.ChangePasswordRequest
import com.example.testapi.domain.model.ChangePasswordResponse
import com.example.testapi.domain.model.ConfirmEmailRequest
import com.example.testapi.domain.model.ConfirmEmailResponse
import com.example.testapi.domain.model.ForgotPasswordRequest
import com.example.testapi.domain.model.LoginRequest
import com.example.testapi.domain.model.LoginResponse
import com.example.testapi.domain.model.RecoveryCodeRequest
import com.example.testapi.domain.model.RecoveryCodeResponse
import com.example.testapi.domain.model.RecoveryPasswordRequest
import com.example.testapi.domain.model.RegisterRequest
import com.example.testapi.domain.model.TemporaryIdResponse

interface AuthRepository {
    suspend fun getPosts(): List<LoginResponse>

    suspend fun login(request: LoginRequest): LoginResponse

    suspend fun register(request: RegisterRequest): TemporaryIdResponse

    suspend fun confirmEmail(request: ConfirmEmailRequest): ConfirmEmailResponse

    suspend fun forgotPassword(request: ForgotPasswordRequest): TemporaryIdResponse

    suspend fun recoveryPassword(request: RecoveryPasswordRequest): LoginResponse

    suspend fun recoveryCode(request: RecoveryCodeRequest): RecoveryCodeResponse

    suspend fun changePassword(request: ChangePasswordRequest): ChangePasswordResponse

    suspend fun changeRole() : String

    suspend fun logout()

}