package com.bookislife.kandroid365.ui.component

import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R
import com.bookislife.kandroid365.extension.show
import kotlinx.android.synthetic.main.activity_simple.*
import org.jetbrains.anko.onClick

/**
 * Toast Showcase
 *
 * Created by SidneyXu on 2016/05/13.
 */
class ToastActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple)
        setUpToolbar()

        button1.show()
        button1.text = getString(R.string.custom_style)
        button1.onClick {
            val layout = layoutInflater.inflate(R.layout.item_toast, null)
            with(Toast(this)) {
                duration = Toast.LENGTH_SHORT
                setGravity(Gravity.CENTER_VERTICAL, 0, 0)
                view = layout
                show()
            }

        }
    }
}