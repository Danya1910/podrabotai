package com.example.testapi.data.mapper

import com.example.testapi.data.dto.request.AdvertisementFilterDto

fun AdvertisementFilterDto.toQueryMap() : Map<String, String> {
    val map = mutableMapOf<String, String>()

    car?.let {map["car"] = it.toString()}
    salary?.let {map["salary"] = it.toString()}
    age?.let {map["age"] = it.toString()}
    xp?.let {map["xp"] = it.toString()}
    address?.let {map["address"] = it.toString()}
    date?.let {map["date"] = it.toString()}

    return map
}