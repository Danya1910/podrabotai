package com.example.testapi.data.api

import com.example.testapi.data.dto.request.AddToFavoriteRequestDto
import com.example.testapi.data.dto.request.AddToHistoryRequestDto
import com.example.testapi.data.dto.request.ChangePasswordRequestDto
import com.example.testapi.data.dto.request.ConfirmEmailRequestDto
import com.example.testapi.data.dto.request.CreateAdvertisementRequestDto
import com.example.testapi.data.dto.request.CreateChatRequestDto
import com.example.testapi.data.dto.request.DeleteFromFavoriteRequestDto
import com.example.testapi.data.dto.request.ForgotPasswordRequestDto
import com.example.testapi.data.dto.request.LoginRequestDto
import com.example.testapi.data.dto.request.RecoveryCodeRequestDto
import com.example.testapi.data.dto.request.RecoveryPasswordRequestDto
import com.example.testapi.data.dto.request.RegisterRequestDto
import com.example.testapi.data.dto.request.SendMessageRequestDto
import com.example.testapi.data.dto.request.SendMessageResponseDto
import com.example.testapi.data.dto.request.UpdateAdvertisementRequestDto
import com.example.testapi.data.dto.response.AddToFavoriteWrapperDto
import com.example.testapi.data.dto.response.AddToHistoryResponseDto
import com.example.testapi.data.dto.response.AdvertisementWrapperDto
import com.example.testapi.data.dto.response.ChangePasswordResponseDto
import com.example.testapi.data.dto.response.ChatMessagesResponseDto
import com.example.testapi.data.dto.response.ConfirmEmailResponseDto
import com.example.testapi.data.dto.response.CreateAdvertisementResponseDto
import com.example.testapi.data.dto.response.CreateChatResponseDto
import com.example.testapi.data.dto.response.DeleteAdvertisementResponseDto
import com.example.testapi.data.dto.response.DeleteFromFavoriteWrapperDto
import com.example.testapi.data.dto.response.DetailedAdvertisementWrapperDto
import com.example.testapi.data.dto.response.GetChatsWrapperDto
import com.example.testapi.data.dto.response.GetCitiesResponseDto
import com.example.testapi.data.dto.response.GetFavoritesResponseDto
import com.example.testapi.data.dto.response.GetHistoryResponseDto
import com.example.testapi.data.dto.response.GetProfileResponseDto
import com.example.testapi.data.dto.response.LoginResponseDto
import com.example.testapi.data.dto.response.RecoveryCodeResponseDto
import com.example.testapi.data.dto.response.TemporaryIdResponseDto
import com.example.testapi.data.dto.response.UpdateAdvertisementResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface PostApi {

    //auth
    @POST(value = "auth/login_mail")
    suspend fun login(@Body request: LoginRequestDto): LoginResponseDto

    @POST(value = "auth/register_mail")
    suspend fun register(@Body request: RegisterRequestDto): TemporaryIdResponseDto

    @POST(value = "auth/confirm_mail")
    suspend fun confirmEmail(@Body request: ConfirmEmailRequestDto): ConfirmEmailResponseDto

    @POST(value = "auth/forgot_password")
    suspend fun forgotPassword(@Body request: ForgotPasswordRequestDto): TemporaryIdResponseDto

    @POST(value = "auth/recovery_code")
    suspend fun recoveryCode(@Body request: RecoveryCodeRequestDto): Response<RecoveryCodeResponseDto>

    @POST(value = "auth/recovery_password")
    suspend fun recoveryPassword(@Body request: RecoveryPasswordRequestDto): LoginResponseDto

    @POST(value = "auth/change_password")
    suspend fun changePassword(@Body request: ChangePasswordRequestDto): ChangePasswordResponseDto

    @GET(value = "profile/change_role")
    suspend fun changeRole() : String

    @GET(value = "auth/logout")
    suspend fun logout()

    //advertisements
    @GET("jobs")
    suspend fun getAdvertisements(
        @QueryMap filters: Map<String, String>
    ): AdvertisementWrapperDto

    @GET("jobs/{jobId}")
    suspend fun getDetailedAdvertisement(@Path("jobId") jobId: Int): DetailedAdvertisementWrapperDto

    @POST("jobs")
    suspend fun createAdvertisement(@Body request: CreateAdvertisementRequestDto): CreateAdvertisementResponseDto

    @PATCH("jobs/{jobId}")
    suspend fun updateJob(@Path("jobId") jobId: Int, @Body request: UpdateAdvertisementRequestDto) : UpdateAdvertisementResponseDto


    @DELETE("jobs/{jobId}")
    suspend fun deleteAdvertisement(@Path("jobId") jobId: Int): DeleteAdvertisementResponseDto

    @GET("jobs/me")
    suspend fun getMyAdvertisements() : AdvertisementWrapperDto

    @POST("jobs/favorite")
    suspend fun addToFavorite(@Body request: AddToFavoriteRequestDto): AddToFavoriteWrapperDto

    @HTTP(method = "DELETE", path = "jobs/favorite", hasBody = true)
    suspend fun deleteFromFavorite(@Body request: DeleteFromFavoriteRequestDto): DeleteFromFavoriteWrapperDto

    @GET("jobs/favorite")
    suspend fun getFavorites() : GetFavoritesResponseDto

    @POST("jobs/history")
    suspend fun addToHistory(@Body request: AddToHistoryRequestDto): AddToHistoryResponseDto

    @GET("jobs/history")
    suspend fun getHistory() : GetHistoryResponseDto

    @GET("jobs/cities")
    suspend fun getCities() : GetCitiesResponseDto

    //chat
    @GET("chats")
    suspend fun getChats(): GetChatsWrapperDto

    @GET("chats/{penpalId}/messages")
    suspend fun getChatHistory(@Path("penpalId") penpalId: Int) : ChatMessagesResponseDto

    @POST("chats")
    suspend fun createChat(@Body request: CreateChatRequestDto) : CreateChatResponseDto

    @POST("chats/{penpalId}/messages")
    suspend fun sendMessage(@Path("penpalId") penpalId: Int, @Body request: SendMessageRequestDto) : SendMessageResponseDto


    //profile
    @GET("profile/me")
    suspend fun getProfile() : GetProfileResponseDto


}