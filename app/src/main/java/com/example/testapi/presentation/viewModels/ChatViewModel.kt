package com.example.testapi.presentation.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TransformedText
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapi.domain.model.CreateChatRequest
import com.example.testapi.domain.usecase.CreateChatUseCase
import com.example.testapi.domain.usecase.GetChatHistoryUseCase
import com.example.testapi.domain.usecase.GetChatsUseCase
import com.example.testapi.domain.usecase.SendMessageUseCase
import com.example.testapi.presentation.states.CreateChatState
import com.example.testapi.presentation.states.GetChatHistoryState
import com.example.testapi.presentation.states.GetChatsState
import com.example.testapi.presentation.states.SendMessageState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ChatViewModel @Inject constructor(
    private val getChatsUseCase: GetChatsUseCase,
    private val getChatHistoryUseCase: GetChatHistoryUseCase,
    private val createChatUseCase: CreateChatUseCase,
    private val sendMessageUseCase: SendMessageUseCase
) : ViewModel() {

    private val _getChatsState = mutableStateOf(GetChatsState())
    private val _getChatHistoryState = mutableStateOf(GetChatHistoryState())
    private val _createChatState = mutableStateOf(CreateChatState())
    private val _sendMessageState = mutableStateOf(SendMessageState())

    val getChatsState: State<GetChatsState> = _getChatsState
    val getChatHistoryState: State<GetChatHistoryState> = _getChatHistoryState
    val createChatState: State<CreateChatState> = _createChatState
    val sendMessageState: State<SendMessageState> = _sendMessageState

    fun loadChats() {
        viewModelScope.launch {
            _getChatsState.value = _getChatsState.value.copy(
                isLoading = true
            )
            try {
                _getChatsState.value = _getChatsState.value.copy(
                    isLoading = false,
                    isSuccessful = true,
                    getChats = getChatsUseCase()
                )
            } catch (e: Exception) {
                _getChatsState.value = _getChatsState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun loadHistory(penpalId: Int) {
        viewModelScope.launch {
            _getChatHistoryState.value = _getChatHistoryState.value.copy(
                isLoading = true
            )
            try {
                _getChatHistoryState.value = _getChatHistoryState.value.copy(
                    isLoading = false,
                    isSuccessful = true,
                    getChatHistory = getChatHistoryUseCase(penpalId = penpalId)
                )
            } catch (e: Exception) {
                _getChatHistoryState.value = _getChatHistoryState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun createChat(
        penpalId: Int,
        jobId: Int
    ) {
        viewModelScope.launch {
            _createChatState.value = _createChatState.value.copy(
                isLoading = true
            )
            try {
                _createChatState.value = _createChatState.value.copy(
                    isLoading = false,
                    isSuccessful = true,
                    createChat = createChatUseCase(penpalId = penpalId, jobId = jobId)
                )
            } catch (e: Exception) {
                _createChatState.value = _createChatState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun sendMessage(
        penpalId: Int,
        text: String
    ) {
        viewModelScope.launch {
            _sendMessageState.value = _sendMessageState.value.copy(
                isLoading = true
            )
            try {
                _sendMessageState.value = _sendMessageState.value.copy(
                    isLoading = false,
                    isSuccessful = true,
                    sendMessage = sendMessageUseCase(penpalId = penpalId, text = text)
                )
            } catch (e: Exception){
                _sendMessageState.value = _sendMessageState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun resetSendMessageFlag() {
        _sendMessageState.value = _sendMessageState.value.copy(isSuccessful = false)
    }

}