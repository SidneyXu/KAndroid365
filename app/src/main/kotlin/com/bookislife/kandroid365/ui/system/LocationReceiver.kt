package com.bookislife.kandroid365.ui.system

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationManager
import android.util.Log

/**
 * Created by SidneyXu on 2016/05/18.
 */
open class LocationReceiver : BroadcastReceiver() {

    companion object {

        private val TAG = LocationReceiver::class.java.name
    }


    override fun onReceive(context: Context, intent: Intent) {
        val location = intent.getParcelableExtra<Location>(LocationManager.KEY_LOCATION_CHANGED)
        if (location != null) {
            onLocationReceived(context, location)
            return
        }

        // if we get here, something else has happened
        if (intent.hasExtra(LocationManager.KEY_PROVIDER_ENABLED)) {
            val enabled = intent.getBooleanExtra(LocationManager.KEY_PROVIDER_ENABLED, false)
            onProviderEnabledChanged(enabled)
        }
    }

    protected open fun onProviderEnabledChanged(enabled: Boolean) {
        Log.d(TAG, "Provider " + if (enabled) "enabled" else "disabled")
    }

    protected open fun onLocationReceived(context: Context, location: Location) {
        Log.d(TAG, " Got location from " + location.provider + ": " + location.latitude + ", " + location.longitude)
    }

}