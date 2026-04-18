package com.example.testapi.presentation.viewModels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testapi.domain.model.LocationData
import com.example.testapi.domain.usecase.GetLocationUseCase
import com.example.testapi.presentation.states.GetAdvertisementsState
import com.example.testapi.presentation.states.GetLocationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LocationViewModel @Inject constructor(
    private val getLocationUseCase: GetLocationUseCase
) : ViewModel() {

    private val _locationState = mutableStateOf(GetLocationState())

    val locationState: State<GetLocationState> = _locationState

    fun loadLocation() {
        viewModelScope.launch {
            val location = getLocationUseCase()

            if (location != null) {
                _locationState.value = _locationState.value.copy(
                    isSuccessful = true,
                    location = location
                )
            } else {
                _locationState.value = _locationState.value.copy(
                    isSuccessful = false,
                    error = "Не удалось получить данные"
                )
            }
        }
    }
}