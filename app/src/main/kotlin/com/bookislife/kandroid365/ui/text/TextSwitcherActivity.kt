package com.bookislife.kandroid365.ui.text

import android.os.Bundle
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R
import kotlinx.android.synthetic.main.activity_text_switcher.*
import org.jetbrains.anko.onClick

/**
 * TextSwitcher Showcase
 *
 * Created by SidneyXu on 2016/05/11.
 */
class TextSwitcherActivity : BaseActivity() {

    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_switcher)
        setUpToolbar()

        applyCount()
        btnLike.onClick {
            count++
            applyCount()
        }
    }

    private fun applyCount() {
        tsCounter.setText(String.format(getString(R.string.likes), count))
    }
}