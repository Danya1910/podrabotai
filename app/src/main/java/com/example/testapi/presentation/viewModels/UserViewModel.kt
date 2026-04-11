package com.example.testapi.presentation.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapi.domain.usecase.GetProfileUseCase
import com.example.testapi.presentation.states.GetProfileState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,

) : ViewModel() {

    private val _getProfileState = mutableStateOf(GetProfileState())

    val getProfileState: State<GetProfileState> = _getProfileState

    fun loadProfile() {
        viewModelScope.launch {
            _getProfileState.value = _getProfileState.value.copy(
                isLoading = true
            )
            try {
                _getProfileState.value = _getProfileState.value.copy(
                    isLoading = false,
                    isSuccessful = true,
                    profile = getProfileUseCase()
                )
            } catch (e: Exception) {
                _getProfileState.value = _getProfileState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

}