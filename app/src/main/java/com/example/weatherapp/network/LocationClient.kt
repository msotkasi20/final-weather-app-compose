package com.example.weatherapp.network

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class LocationClient(context: Context) {
    private val fusedLoc = LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    suspend fun getCurrentLocation() : Location? = suspendCancellableCoroutine { cont ->
        val cts = CancellationTokenSource()
        fusedLoc.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, cts.token)
            .addOnSuccessListener { cont.resume(it)}
            .addOnFailureListener { cont.resume(null)}
        cont.invokeOnCancellation { cts.cancel() }
    }
}