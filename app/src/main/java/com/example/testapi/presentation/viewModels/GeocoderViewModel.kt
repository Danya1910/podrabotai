package com.example.testapi.presentation.viewModels

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapi.domain.usecase.GetCityUseCase
import dagger.hilt.android.internal.Contexts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GeocoderViewModel @Inject constructor(
    private val getCityUseCase: GetCityUseCase
) : ViewModel() {

    val city = mutableStateOf("")

    fun loadCity(
        context: Context,
        lat: Double,
        lng: Double
    )  {
        viewModelScope.launch {
            city.value = getCityUseCase(
                context = context,
                lat = lat,
                lng = lng,
            )
            Log.d("Geocoder VM", "city = ${city.value}")
        }
    }

}