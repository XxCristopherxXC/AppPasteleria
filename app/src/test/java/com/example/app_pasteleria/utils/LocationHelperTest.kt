package com.milsabores.pasteleria.utils

import android.content.Context
import android.location.Location
import android.location.LocationManager
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class LocationHelperTest {

    private lateinit var context: Context
    private lateinit var locationManager: LocationManager
    private lateinit var helper: LocationHelper

    @Before
    fun setup() {
        context = mock(Context::class.java)
        locationManager = mock(LocationManager::class.java)
        `when`(context.getSystemService(Context.LOCATION_SERVICE)).thenReturn(locationManager)
        helper = LocationHelper(context)
    }

    @Test
    fun `getUserLocation retorna null cuando no hay ubicacion`() {
        `when`(locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)).thenReturn(null)
        val location = helper.getUserLocation()
        assertThat(location).isNull()
    }

    @Test
    fun `getUserLocation retorna location cuando hay disponible`() {
        val mockLocation = mock(Location::class.java)
        `when`(mockLocation.latitude).thenReturn(10.0)
        `when`(mockLocation.longitude).thenReturn(20.0)
        `when`(locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)).thenReturn(mockLocation)

        val location = helper.getUserLocation()
        assertThat(location).isNotNull()
        assertThat(location?.latitude).isEqualTo(10.0)
        assertThat(location?.longitude).isEqualTo(20.0)
    }
}
