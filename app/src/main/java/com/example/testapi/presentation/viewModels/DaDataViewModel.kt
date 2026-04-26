package com.example.testapi.presentation.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testapi.domain.usecase.GetSuggestionsUseCase
import com.example.testapi.presentation.states.CreateChatState
import com.example.testapi.presentation.states.GetChatHistoryState
import com.example.testapi.presentation.states.GetChatsState
import com.example.testapi.presentation.states.GetSuggestionsState
import com.example.testapi.presentation.states.SendMessageState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.http.Query
import javax.inject.Inject


@HiltViewModel
class DaDataViewModel @Inject constructor(
    private val getSuggestionsUseCase: GetSuggestionsUseCase
) : ViewModel() {

    private val _getSuggestionsState = MutableStateFlow(GetSuggestionsState())

    val getSuggestionsState = _getSuggestionsState.asStateFlow()

    fun loadSuggestions(
        query: String
    ) {
        viewModelScope.launch {
            _getSuggestionsState.update {
                it.copy(
                    isLoading = true
                )
            }
            try {
                _getSuggestionsState.update {
                    it.copy(
                    isLoading = false,
                    isSuccessful = true,
                    suggestions = getSuggestionsUseCase(query = query)
                )
                    }
            } catch (e: Exception) {
                _getSuggestionsState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message
                    )
                }
            }
        }
    }

    fun clearSuggestions() {
        _getSuggestionsState.update {
            it.copy(
                suggestions = null
            )
        }
    }
}