package com.example.testapi.data.repository

import android.util.Log
import com.example.testapi.data.api.PostApi
import com.example.testapi.data.mapper.toCreateDto
import com.example.testapi.data.mapper.toDomain
import com.example.testapi.data.socket.SocketManager
import com.example.testapi.domain.model.ChatMessages
import com.example.testapi.domain.model.CreateChatRequest
import com.example.testapi.domain.model.CreateChatResponse
import com.example.testapi.domain.model.GetChatsResponse
import com.example.testapi.domain.model.SendMessageRequest
import com.example.testapi.domain.model.SendMessageResponse
import com.example.testapi.domain.repository.AdvertisementRepository
import com.example.testapi.domain.repository.ChatRepository
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val api: PostApi,
    private val socketManager: SocketManager,
) : ChatRepository {

    override suspend fun getChats(): List<GetChatsResponse> {
        Log.d("REPO_DEBUG", "getChats called")
         return api.getChats().data.map { it.toDomain() }
    }

    override suspend fun getChatHistory(penpalId: Int): ChatMessages {
        return api.getChatHistory(penpalId = penpalId).toDomain()
    }

    override suspend fun createChat(request: CreateChatRequest): CreateChatResponse {
        Log.d("REPO_DEBUG", "createChat called")
        Log.d("REPO_DEBUG", "${api.createChat(request = request.toCreateDto()).toDomain()}")

        return api.createChat(request = request.toCreateDto()).toDomain()
    }

    override suspend fun sendMessage(penpalId: Int, request: SendMessageRequest): SendMessageResponse {
        Log.d("REPO_DEBUG", "sendMessage called")
        return api.sendMessage(penpalId = penpalId, request = request.toCreateDto()).toDomain()
    }

    override fun observePing(): Flow<Int> {
        return socketManager.pingFlow
    }

}