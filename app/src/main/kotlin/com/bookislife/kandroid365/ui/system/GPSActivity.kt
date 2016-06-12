package com.bookislife.kandroid365.ui.system

import android.content.Context
import android.content.IntentFilter
import android.location.Location
import android.os.Bundle
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R
import kotlinx.android.synthetic.main.activity_location.*
import org.jetbrains.anko.enabled
import org.jetbrains.anko.onClick
import org.jetbrains.anko.toast
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit

/**
 * GPS Showcase
 *
 * Created by SidneyXu on 2016/05/18.
 */
class GPSActivity : BaseActivity() {

    var monitor = lazy {
        LocationMonitor.getInstance(this)
    }
    var lastLocation: Location? = null
    var duration: Duration? = null
    val executor: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor()
    var future: ScheduledFuture<*>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)
        setUpToolbar()

        btnStart.onClick {
            monitor.value.startLocationUpdates()
            duration = Duration()
            updateUI()
        }
        btnStop.onClick {
            monitor.value.stopLocationUpdates()
            updateUI()
        }

        monitor.value.registerMockProvider()
        val random = Random()
        btnStartFake.onClick {
            future = executor.scheduleAtFixedRate({
                monitor.value.sendMockLocation(random.nextInt(90).toDouble(), random.nextInt(90).toDouble(), random.nextInt(90).toDouble())
            }, 0, 3000, TimeUnit.MILLISECONDS)
            btnStartFake.enabled = false
            btnStopFake.enabled = true
        }
        btnStopFake.onClick {
            future?.cancel(true)
            btnStartFake.enabled = true
            btnStopFake.enabled = false
        }

        btnStartFake.enabled = true
        btnStopFake.enabled = false

        updateUI()
    }

    val locationReceiver = object : LocationReceiver() {
        override fun onLocationReceived(context: Context, location: Location) {
            super.onLocationReceived(context, location)
            lastLocation = location
            updateUI()
        }

        override fun onProviderEnabledChanged(enabled: Boolean) {
            super.onProviderEnabledChanged(enabled)
            toast(if (enabled) "GPS enabled" else "GPS disabled")
        }
    }

    private fun updateUI() {
        val started = monitor.value.isRunning()
        var durationSeconds = 0
        if (lastLocation != null) {
            durationSeconds = duration!!.getDurationSeconds(lastLocation!!.time)
            tvLatitude.text = "" + lastLocation!!.latitude
            tvLongitude.text = "" + lastLocation!!.longitude
            tvAltitude.text = "" + lastLocation!!.altitude
        }
        tvDuration.text = Duration.formatDuration(durationSeconds)

        btnStart.isEnabled = !started
        btnStop.isEnabled = started
    }

    override fun onStart() {
        super.onStart()
        registerReceiver(locationReceiver, IntentFilter(LocationMonitor.ACTION_LOCATION))
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(locationReceiver)
        if (!executor.isTerminated) {
            executor.shutdown()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        monitor.value.unregisterMockProvider()
    }
}