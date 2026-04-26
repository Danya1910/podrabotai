package com.example.testapi.presentation.viewModels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapi.data.socket.SocketManager
import com.example.testapi.domain.usecase.CreateChatUseCase
import com.example.testapi.domain.usecase.GetChatHistoryUseCase
import com.example.testapi.domain.usecase.GetChatsUseCase
import com.example.testapi.domain.usecase.ObservePingUseCase
import com.example.testapi.domain.usecase.SendMessageUseCase
import com.example.testapi.presentation.states.CreateChatState
import com.example.testapi.presentation.states.GetChatHistoryState
import com.example.testapi.presentation.states.GetChatsState
import com.example.testapi.presentation.states.SendMessageState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ChatViewModel @Inject constructor(
    private val getChatsUseCase: GetChatsUseCase,
    private val getChatHistoryUseCase: GetChatHistoryUseCase,
    private val createChatUseCase: CreateChatUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
    private val observePingUseCase: ObservePingUseCase,
    private val socketManager: SocketManager,
) : ViewModel() {

    private val _getChatsState = MutableStateFlow(GetChatsState())
    private val _getChatHistoryState = MutableStateFlow(GetChatHistoryState())
    private val _createChatState = MutableStateFlow(CreateChatState())
    private val _sendMessageState = MutableStateFlow(SendMessageState())
    private val _currentPenpalId = MutableStateFlow<Int?>(null)

    val getChatsState = _getChatsState.asStateFlow()
    val getChatHistoryState = _getChatHistoryState.asStateFlow()
    val createChatState = _createChatState.asStateFlow()
    val sendMessageState = _sendMessageState.asStateFlow()

    fun setCurrentPenpal(penpalId: Int) {
        _currentPenpalId.value = penpalId
    }

    init {
        observePing()
    }

    private fun observePing() {
        viewModelScope.launch {
            observePingUseCase().collect { penpalId ->
                Log.d(
                    "ChatVM_Debug",
                    "Socket says: $penpalId, My current screen ID is: ${_currentPenpalId.value}"
                )
                if (_currentPenpalId.value == penpalId) {
                    loadHistory(penpalId)
                } else {
                    Log.d(
                        "ChatVM_Debug",
                        "ID MISMATCH: Expected ${_currentPenpalId.value}, got $penpalId"
                    )
                }
            }
        }
    }

    fun loadChats() {
        viewModelScope.launch {
            _getChatsState.update {
                it.copy(
                    isLoading = true
                )
            }
            try {
                _getChatsState.update {
                    it.copy(
                        isLoading = false,
                        isSuccessful = true,
                        chats = getChatsUseCase()
                    )
                }
            } catch (e: Exception) {
                _getChatsState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message
                    )
                }
            }
        }
    }

    fun loadHistory(penpalId: Int) {
        viewModelScope.launch {
            _getChatHistoryState.update {
                it.copy(
                    isLoading = true
                )
            }
            try {
                _getChatHistoryState.update {
                    it.copy(
                        isLoading = false,
                        isSuccessful = true,
                        chatHistory = getChatHistoryUseCase(penpalId = penpalId)
                    )
                }
            } catch (e: Exception) {
                _getChatHistoryState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message
                    )
                }
            }
        }
    }

    fun createChat(
        penpalId: Int,
        jobId: Int
    ) {
        viewModelScope.launch {
            _createChatState.update {
                it.copy(
                    isLoading = true
                )
            }
            try {
                _createChatState.update {
                    it.copy(
                        isLoading = false,
                        isSuccessful = true,
                        createChat = createChatUseCase(penpalId = penpalId, jobId = jobId)
                    )
                }
            } catch (e: Exception) {
                _createChatState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message
                    )
                }
            }
        }
    }

    fun sendMessage(
        penpalId: Int,
        text: String
    ) {
        viewModelScope.launch {
            _sendMessageState.update {
                it.copy(
                    isLoading = true
                )
            }
            try {
                _sendMessageState.update {
                    it.copy(
                        isLoading = false,
                        isSuccessful = true,
                        sendMessage = sendMessageUseCase(penpalId = penpalId, text = text)
                    )
                }
            } catch (e: Exception) {
                _sendMessageState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message
                    )
                }
            }
        }
    }

    fun resetSendMessageFlag() {
        _sendMessageState.update {
            it.copy(isSuccessful = false)
        }
    }

}