package com.example.testapi.presentation.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapi.domain.usecase.GetPositionUseCase
import com.example.testapi.presentation.states.GetAdvertisementsState
import com.example.testapi.presentation.states.GetPositionState
import dagger.hilt.android.lifecycle.HiltViewModel
import hilt_aggregated_deps._com_example_testapi_MainActivity_GeneratedInjector
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BigDataCloudViewModel @Inject constructor(
    private val getPositionUseCase: GetPositionUseCase
) : ViewModel() {

    private val _positionState = mutableStateOf(GetPositionState())

    val positionState: State<GetPositionState> = _positionState

    fun loadPosition(lat: Double, lng: Double) {
        viewModelScope.launch {
            _positionState.value = _positionState.value.copy(
                isLoading = true
            )
            try{
                _positionState.value = _positionState.value.copy(
                    isLoading = false,
                    isSuccessful = true,
                    position = getPositionUseCase(lat = lat, lng = lng)
                )
            } catch (e: Exception) {
                _positionState.value = _positionState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

}