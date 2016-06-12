package com.bookislife.kandroid365.ui.component

import android.os.Bundle
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R
import kotlinx.android.synthetic.main.activity_progress.*
import kotlin.concurrent.timer

/**
 * ProgressBar Showcase
 *
 * Created by SidneyXu on 2016/05/13.
 */
class ProgressActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress)
        setUpToolbar()

        timer(period = 300) {
            progress2.progress += 10

            progress3.progress += 10
            progress3.secondaryProgress += 20

            if (progress2.progress >= 100) {
                progress2.progress = 0
            }
            if (progress3.progress >= 100) {
                progress3.progress = 0
            }
            if (progress3.secondaryProgress >= 100) {
                progress3.secondaryProgress = 0
            }
        }
    }
}