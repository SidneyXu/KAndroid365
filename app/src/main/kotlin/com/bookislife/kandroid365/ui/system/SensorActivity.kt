package com.bookislife.kandroid365.ui.system

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R
import kotlinx.android.synthetic.main.activity_sensor.*

/**
 * Sensor Showcase
 *
 * Created by SidneyXu on 2016/05/24.
 */
class SensorActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensor)
        setUpToolbar()
    }

    override fun onStart() {
        super.onStart()
        registerLightSensor()
    }

    override fun onStop() {
        unregisterLightSensor()
        super.onStop()
    }

    private fun registerLightSensor() {
        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    private fun unregisterLightSensor() {
        (getSystemService(Context.SENSOR_SERVICE) as SensorManager).unregisterListener(listener)
    }

    private val listener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent) {
            val value = event.values[0]
            tvBrightness.text = value.toString()
        }

        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        }
    }
}
