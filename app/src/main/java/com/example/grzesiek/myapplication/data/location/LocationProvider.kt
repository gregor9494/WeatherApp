package com.example.grzesiek.myapplication.data.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.example.grzesiek.myapplication.data.models.error.NoLocationFoundException
import com.google.android.gms.location.LocationRequest
import io.reactivex.Observable
import io.reactivex.Single
import pl.charmas.android.reactivelocation2.ReactiveLocationProvider
import java.util.concurrent.TimeUnit

@SuppressLint("MissingPermission")
class LocationProvider(context: Context) {
    val reactiveLocationProvider = ReactiveLocationProvider(context)

    fun requestLocation(): Single<Location> {
        return reactiveLocationProvider.getUpdatedLocation(createRequest())
                .timeout(5, TimeUnit.SECONDS, lastKnownLocation())
                .singleOrError()
    }

    private fun lastKnownLocation() =
            reactiveLocationProvider.lastKnownLocation.switchIfEmpty(Observable.error(NoLocationFoundException()))

    fun createRequest(): LocationRequest {
        return LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
                .setNumUpdates(1)
                .setInterval(1000)
    }
}