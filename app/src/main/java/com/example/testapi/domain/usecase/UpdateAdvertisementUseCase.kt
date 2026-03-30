package com.example.testapi.domain.usecase

import com.example.testapi.domain.model.CreateAdvertisementRequest
import com.example.testapi.domain.model.CreateAdvertisementResponse
import com.example.testapi.domain.model.UpdateAdvertisementRequest
import com.example.testapi.domain.model.UpdateAdvertisementResponse
import com.example.testapi.domain.repository.AdvertisementRepository
import javax.inject.Inject

class UpdateAdvertisementUseCase @Inject constructor(
    private val repository: AdvertisementRepository
) {

    suspend operator fun invoke(
        title: String? = null,
        wantedJob: String? = null,
        description: String? = null,
        salary: Int? = null,
        date: String? = null,
        timeStart: String? = null,
        timeEnd: String? = null,
        address: String? = null,
        cityId: Int? = null,
        city: String? = null,
        xp: Int? = null,
        age: Int? = null,
        isUrgent: Boolean? = null,
        car: Boolean? = null,
        jobId: Int
    ): UpdateAdvertisementResponse {
        val request = UpdateAdvertisementRequest(
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
        return repository.updateAdvertisement(
            request = request,
            jobId = jobId
        )
    }

}