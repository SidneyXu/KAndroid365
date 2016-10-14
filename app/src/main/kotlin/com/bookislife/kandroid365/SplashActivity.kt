package com.bookislife.kandroid365

import android.app.Activity
import android.os.Bundle
import android.os.CountDownTimer
import com.bookislife.kandroid365.extension.end

/**
 * Created by Sidney on 2016/8/17.
 */
class SplashActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        object : CountDownTimer(2000, 1000) {
            override fun onFinish() {
                end(MainActivity::class.java)
            }

            override fun onTick(millisUntilFinished: Long) {
            }

        }.start()
    }
}