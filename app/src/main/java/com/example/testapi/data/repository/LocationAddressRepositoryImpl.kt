package com.example.testapi.data.repository

import android.content.Context
import android.location.Geocoder
import android.os.Build
import com.example.testapi.domain.repository.LocationAddressRepository
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.Locale
import javax.inject.Inject
import kotlin.coroutines.resume

class LocationAddressRepositoryImpl @Inject constructor(

) : LocationAddressRepository {

    override suspend fun getCity(context: Context, lat: Double, lng: Double): String {
        val geocoder = Geocoder(context, Locale("ru"))

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            suspendCancellableCoroutine { cont ->
                geocoder.getFromLocation(lat, lng, 1) { list ->
                    val city = list.firstOrNull()?.locality
                        ?:list.firstOrNull()?.subAdminArea
                        ?:list.firstOrNull()?.adminArea
                        ?:"Неизвестно"
                    cont.resume(city)
                }
            }
        }
        else {
            val list = geocoder.getFromLocation(lat, lng, 1)

            list?.firstOrNull()?.locality
                ?:list?.firstOrNull()?.subAdminArea
                ?:list?.firstOrNull()?.adminArea
                ?:"Неизвестно"
        }
    }
}