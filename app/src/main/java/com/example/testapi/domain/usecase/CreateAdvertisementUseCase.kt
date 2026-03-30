package com.example.testapi.domain.usecase

import android.util.Log
import com.example.testapi.domain.model.CreateAdvertisementRequest
import com.example.testapi.domain.model.CreateAdvertisementResponse
import com.example.testapi.domain.repository.AdvertisementRepository
import javax.inject.Inject

class CreateAdvertisementUseCase @Inject constructor(
    private val repository: AdvertisementRepository
) {
    suspend operator fun invoke(
        title: String,
        wantedJob: String,
        description: String,
        salary: Int,
        date: String,
        timeStart: String,
        timeEnd: String,
        address: String,
        cityId: Int,
        city: String,
        xp: Int,
        age: Int,
        isUrgent: Boolean,
        car: Boolean
    ) : CreateAdvertisementResponse {
        val request = CreateAdvertisementRequest(
            title = title,
            wantedJob = wantedJob,
            description = description,
            salary = salary,
            date = date,
            timeStart = timeStart,
            timeEnd = timeEnd,
            address = address,
            cityId = cityId,
            city = city,
            xp = xp,
            age = age,
            isUrgent = isUrgent,
            car = car
        )
        Log.d("CreateAd Request", "$request")
        return repository.createAdvertisement(request = request)
    }
}