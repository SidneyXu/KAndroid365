package com.bookislife.kandroid365.ui.window

import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.view.View
import android.widget.PopupMenu
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R
import com.bookislife.kandroid365.extension.show
import kotlinx.android.synthetic.main.activity_simple.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.toast

/**
 * PopupMenu Showcase
 *
 * Created by SidneyXu on 2016/05/16.
 */
class PopupActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple)
        setUpToolbar()

        button1.apply {
            show()
            text = getString(R.string.show)
            onClick {
                popMenu(this)
            }
        }
    }

    private fun popMenu(anchor: View) {
        val popup = PopupMenu(this, anchor, GravityCompat.END)
        popup.menuInflater.inflate(R.menu.activity_main_drawer, popup.menu)
        popup.setOnMenuItemClickListener {
            toast("Click ${it.title}")
            false
        }
        popup.show()
    }
}