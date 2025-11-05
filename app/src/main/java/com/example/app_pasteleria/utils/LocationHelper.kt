package com.milsabores.pasteleria.utils

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.widget.Toast

class LocationHelper(private val context: Context) {

    @SuppressLint("MissingPermission")
    fun getUserLocation(): Location? {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        if (location != null) {
            Toast.makeText(
                context,
                "Ubicación: ${location.latitude}, ${location.longitude}",
                Toast.LENGTH_LONG
            ).show()
        } else {
            Toast.makeText(context, "No se pudo obtener la ubicación", Toast.LENGTH_SHORT).show()
        }
        return location
    }
}
