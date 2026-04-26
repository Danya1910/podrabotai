package com.example.testapi.domain.usecase

import android.content.Context
import com.example.testapi.domain.repository.LocationAddressRepository
import javax.inject.Inject

class GetCityUseCase @Inject constructor(
    private val repository: LocationAddressRepository
) {

    suspend operator fun invoke(
        context: Context,
        lat: Double,
        lng: Double,
    ) : String {
        return repository.getCity(context = context, lat = lat, lng = lng)
    }

}