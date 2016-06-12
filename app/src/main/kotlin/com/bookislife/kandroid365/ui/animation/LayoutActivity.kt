package com.bookislife.kandroid365.ui.animation

import android.animation.LayoutTransition
import android.os.Bundle
import android.widget.Button
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R
import kotlinx.android.synthetic.main.activity_layout.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.textColor

/**
 * Layout Animation Showcase
 *
 * Created by SidneyXu on 2016/06/10.
 */
class LayoutActivity : BaseActivity() {

    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)
        setUpToolbar()

        grid.columnCount = 3
        grid.layoutTransition = LayoutTransition()

        btnAdd.onClick {
            val button = Button(this)
            button.text = "button ${count++}"
            button.textColor = R.color.textColorPrimary
            button.onClick {
                grid.removeView(button)
            }
            grid.addView(button)
        }
    }
}