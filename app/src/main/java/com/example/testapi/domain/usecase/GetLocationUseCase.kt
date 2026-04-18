package com.example.testapi.domain.usecase

import com.example.testapi.domain.model.LocationData
import com.example.testapi.domain.repository.LocationRepository
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(
    private val repository: LocationRepository
) {

    suspend operator fun invoke() : LocationData? {
        return repository.getLocation()
    }

}