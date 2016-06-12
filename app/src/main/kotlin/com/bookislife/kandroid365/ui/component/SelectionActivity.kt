package com.bookislife.kandroid365.ui.component

import android.os.Bundle
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R

/**
 * CheckBox & RadioButton & Switch Showcase
 *
 * Created by SidneyXu on 2016/05/16.
 */
class SelectionActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selection)
        setUpToolbar()
    }
}