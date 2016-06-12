package com.bookislife.kandroid365

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import org.jetbrains.anko.find

/**
 * Created by SidneyXu on 2016/03/14.
 */
abstract class BaseActivity : AppCompatActivity() {

    protected  var toolbar: Toolbar? = null

    protected fun setUpToolbar() {
        toolbar = find<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = javaClass.simpleName
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }
}