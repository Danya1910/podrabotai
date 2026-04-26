package com.example.testapi.presentation.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapi.domain.usecase.GetProfileUseCase
import com.example.testapi.presentation.states.GetProfileState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,

) : ViewModel() {

    private val _getProfileState = MutableStateFlow(GetProfileState())

    val getProfileState = _getProfileState.asStateFlow()

    fun loadProfile() {
        viewModelScope.launch {
            _getProfileState.update {
                it.copy(
                    isLoading = true
                )
            }
            try {
                _getProfileState.update {
                    it.copy(
                        isLoading = false,
                        isSuccessful = true,
                        profile = getProfileUseCase()
                    )
                }
            } catch (e: Exception) {
                _getProfileState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message
                    )
                }
            }
        }
    }

}