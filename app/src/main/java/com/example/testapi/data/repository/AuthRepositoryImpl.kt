package com.example.testapi.data.repository

import android.util.Log
import com.example.testapi.data.api.PostApi
import com.example.testapi.data.mapper.toCreateDto
import com.example.testapi.data.mapper.toDomain
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
import com.example.testapi.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: PostApi
) : AuthRepository {
    override suspend fun getPosts() : List<LoginResponse> {
        return api.getPosts().map { it.toDomain() }
    }

    override suspend fun login(request: LoginRequest) : LoginResponse{
        return api.login(request.toCreateDto()).toDomain()
    }

    override suspend fun register(request: RegisterRequest): TemporaryIdResponse {
        return api.register(request.toCreateDto()).toDomain()
    }

    override suspend fun confirmEmail(request: ConfirmEmailRequest): ConfirmEmailResponse {
        return api.confirmEmail(request = request.toCreateDto()).toDomain()
    }

    override suspend fun forgotPassword(request: ForgotPasswordRequest): TemporaryIdResponse {
        return api.forgotPassword(request = request.toCreateDto()).toDomain()
    }

    override suspend fun recoveryPassword(request: RecoveryPasswordRequest): LoginResponse {
        return api.recoveryPassword(request = request.toCreateDto()).toDomain()
    }

    override suspend fun changePassword(request: ChangePasswordRequest) : ChangePasswordResponse {
        return api.changePassword(request = request.toCreateDto()).toDomain()
    }

    override suspend fun recoveryCode(
        request: RecoveryCodeRequest
    ): RecoveryCodeResponse {

        val response = api.recoveryCode(request.toCreateDto())

        return RecoveryCodeResponse(
            message = response.body()?.message ?: "Неизветсная ошибка",
            statusCode = response.code()
        )
    }

    override suspend fun changeRole() : String {
        Log.d("REPO_DEBUG", "changeRole called")
        val result = api.changeRole()
        Log.d("REPO_DEBUG", result)
        return result
    }

    override suspend fun logout() {
        Log.d("REPO_DEBUG", "logout called")
        api.logout()
    }
}

