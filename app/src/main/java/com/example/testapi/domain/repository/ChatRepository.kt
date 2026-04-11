package com.example.testapi.domain.repository

import com.example.testapi.domain.model.ChatMessages
import com.example.testapi.domain.model.CreateChatRequest
import com.example.testapi.domain.model.CreateChatResponse
import com.example.testapi.domain.model.GetChatsResponse
import com.example.testapi.domain.model.SendMessageRequest
import com.example.testapi.domain.model.SendMessageResponse
import kotlinx.coroutines.flow.Flow

interface ChatRepository {

    suspend fun getChats(): List<GetChatsResponse>

    suspend fun getChatHistory(penpalId: Int): ChatMessages

    suspend fun createChat(request: CreateChatRequest): CreateChatResponse

    suspend fun sendMessage(penpalId: Int, request: SendMessageRequest): SendMessageResponse

    fun observePing(): Flow<Int>

}