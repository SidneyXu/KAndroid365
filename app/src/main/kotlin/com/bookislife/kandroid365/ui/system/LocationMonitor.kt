package com.bookislife.kandroid365.ui.system

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.location.Criteria
import android.location.Location
import android.location.LocationManager
import android.location.LocationProvider
import android.os.Build
import com.bookislife.kandroid365.extension.log

/**
 * Created by SidneyXu on 2016/05/18.
 */
class LocationMonitor private constructor(context: Context) {

    companion object {

        private val TAG = LocationManager::class.java.name

        private val TEST_PROVIDER = "TEST_PROVIDER"

        val ACTION_LOCATION = "kandroid365.location.ACTION_LOCATION"

        private var instance: LocationMonitor? = null

        fun getInstance(context: Context): LocationMonitor {
            if (instance == null) {
                instance = LocationMonitor(context)
            }
            return instance!!
        }
    }

    private val applicationContext: Context
    private val locationManager: LocationManager


    init {
        this.applicationContext = context.applicationContext
        locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    fun startLocationUpdates() {
        var provider = LocationManager.GPS_PROVIDER
        // if we have the test provider and it's enabled, use it
        if (locationManager.getProvider(TEST_PROVIDER) != null && locationManager.isProviderEnabled(TEST_PROVIDER)) {
            provider = TEST_PROVIDER
        }
        applicationContext.log("Using provider $provider")

        // get the last known location and broadcast it if we have one
        val lastKnown = locationManager.getLastKnownLocation(provider)
        if (lastKnown != null) {
            // reset the time to now
            lastKnown.time = System.currentTimeMillis()
            broadcastLocation(lastKnown)
        }
        // start updates from the location manager
        val pi = getLocationPendingIntent(true)
        locationManager.requestLocationUpdates(provider, 0, 0f, pi)
    }

    fun registerMockProvider() {
        if (!locationManager.allProviders.contains(TEST_PROVIDER)) {
            locationManager.addTestProvider(TEST_PROVIDER, false, false, false, false, true, true, true, Criteria.POWER_MEDIUM, Criteria.ACCURACY_FINE)
            locationManager.setTestProviderEnabled(TEST_PROVIDER, true)
            locationManager.setTestProviderStatus(TEST_PROVIDER, LocationProvider.AVAILABLE, null, System.currentTimeMillis())
        }
    }

    fun unregisterMockProvider() {
        if (locationManager.allProviders.contains(TEST_PROVIDER)) {
            locationManager.removeTestProvider(TEST_PROVIDER)
        }
    }

    fun sendMockLocation(latitude: Double, longitude: Double, altitude: Double) {
        val location = Location(TEST_PROVIDER)
        location.latitude = latitude
        location.longitude = longitude
        location.altitude = altitude
        location.accuracy = 1000F
        location.time = System.currentTimeMillis()
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
            location.elapsedRealtimeNanos = location.time
        }
        locationManager.setTestProviderLocation(TEST_PROVIDER, location)
    }

    private fun getLocationPendingIntent(shouldCreate: Boolean): PendingIntent? {
        val broadcast = Intent(ACTION_LOCATION)
        val flags = if (shouldCreate) 0 else PendingIntent.FLAG_NO_CREATE
        return PendingIntent.getBroadcast(applicationContext, 0, broadcast, flags)
    }

    private fun broadcastLocation(location: Location) {
        val broadcast = Intent(ACTION_LOCATION)
        broadcast.putExtra(LocationManager.KEY_LOCATION_CHANGED, location)
        applicationContext.sendBroadcast(broadcast)
    }

    fun isRunning(): Boolean = getLocationPendingIntent(false) != null

    fun stopLocationUpdates() {
        val pi = getLocationPendingIntent(false)
        if (pi != null) {
            locationManager.removeUpdates(pi)
            pi.cancel()
        }
    }


}