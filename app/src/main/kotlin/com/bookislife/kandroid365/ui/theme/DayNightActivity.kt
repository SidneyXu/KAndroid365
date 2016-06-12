package com.bookislife.kandroid365.ui.theme

import android.os.Bundle
import android.support.v7.app.AppCompatDelegate
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R
import kotlinx.android.synthetic.main.activity_daynight.*
import org.jetbrains.anko.onClick

/**
 * Day & Night Theme Showcase
 *
 * Created by SidneyXu on 2016/05/17.
 */
class DayNightActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daynight)
        setUpToolbar()

        // Dependency on last known location and date time
        text1.text = when (AppCompatDelegate.getDefaultNightMode()) {
            AppCompatDelegate.MODE_NIGHT_YES -> getString(R.string.night)
            AppCompatDelegate.MODE_NIGHT_NO -> getString(R.string.day)
            else -> getString(R.string.auto)
        }

        btnAuto.onClick {
            setMode(AppCompatDelegate.MODE_NIGHT_AUTO)
            recreate()
        }

        btnDay.onClick {
            setMode(AppCompatDelegate.MODE_NIGHT_NO)
            recreate()
        }
        btnNight.onClick {
            setMode(AppCompatDelegate.MODE_NIGHT_YES)
            recreate()
        }
    }

    private fun setMode(mode: Int) {
        text1.text = when (mode) {
            AppCompatDelegate.MODE_NIGHT_YES -> getString(R.string.night)
            AppCompatDelegate.MODE_NIGHT_NO -> getString(R.string.day)
            else -> getString(R.string.auto)
        }
        AppCompatDelegate.setDefaultNightMode(mode)
    }
}