package com.bookislife.kandroid365.ui.component

import android.os.Bundle
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R

/**
 * RatingBar Showcase
 *
 * Created by SidneyXu on 2016/05/13.
 */
class RatingActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating)
        setUpToolbar()
    }
}