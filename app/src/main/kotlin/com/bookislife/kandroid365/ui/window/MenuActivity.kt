package com.bookislife.kandroid365.ui.window

import android.os.Bundle
import android.view.Menu
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R

/**
 * Menu Showcase
 *
 * Created by SidneyXu on 2016/05/17.
 */
class MenuActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple)
        setUpToolbar()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add(1, 1, 0, getString(R.string.show))
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
}