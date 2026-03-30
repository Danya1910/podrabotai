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
import kotlinx.coroutines.launch
import retrofit2.http.Query
import javax.inject.Inject


@HiltViewModel
class DaDataViewModel @Inject constructor(
    private val getSuggestionsUseCase: GetSuggestionsUseCase
) : ViewModel() {

    private val _getSuggestionsState = mutableStateOf(GetSuggestionsState())

    val getSuggestionsState: State<GetSuggestionsState> = _getSuggestionsState

    fun loadSuggestions(
        query: String
    ) {
        viewModelScope.launch {
            _getSuggestionsState.value = _getSuggestionsState.value.copy(
                isLoading = true
            )
            try {
                _getSuggestionsState.value = _getSuggestionsState.value.copy(
                    isLoading = false,
                    isSuccessful = true,
                    suggestions = getSuggestionsUseCase(query = query)
                )
            } catch (e: Exception) {
                _getSuggestionsState.value = _getSuggestionsState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun clearSuggestions() {
        _getSuggestionsState.value = _getSuggestionsState.value.copy(
            suggestions = null
        )
    }
}