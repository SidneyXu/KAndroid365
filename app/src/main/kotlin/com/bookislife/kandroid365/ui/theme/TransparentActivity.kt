package com.bookislife.kandroid365.ui.theme

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Gravity
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R
import org.jetbrains.anko.*

/**
 * Transparent Activity Showcase
 *
 * Created by SidneyXu on 2016/05/15.
 */
class TransparentActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val view = UI {
            linearLayout {
                gravity = Gravity.CENTER
                button(R.string.raised_button) {
                    textColor = ContextCompat.getColor(this@TransparentActivity, R.color.textColorPrimary)
                    onClick {
                        alert {
                            title("FooBar")
                            message("Hello World")
                            cancellable(true)
                        }.show()
                    }
                }
            }
        }.view
        setContentView(view)
    }
}