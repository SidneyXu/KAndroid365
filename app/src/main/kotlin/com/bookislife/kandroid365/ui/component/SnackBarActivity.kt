package com.bookislife.kandroid365.ui.component

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.widget.TextView
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R
import kotlinx.android.synthetic.main.activity_snackbar.*
import org.jetbrains.anko.find
import org.jetbrains.anko.onClick
import org.jetbrains.anko.textColor
import org.jetbrains.anko.toast

/**
 * SnackBar Showcase
 *
 * Created by SidneyXu on 2016/05/16.
 */
class SnackBarActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snackbar)
        setUpToolbar()

        btnShow.onClick {
            val snackBar = Snackbar.make(container, getString(android.R.string.ok), Snackbar.LENGTH_SHORT)
            when (rgColumn.checkedRadioButtonId) {
                R.id.rbCustom -> {
                    val text = snackBar.view.find<TextView>(android.support.design.R.id.snackbar_text)
                    text.textColor = Color.YELLOW
                    snackBar
                            .setActionTextColor(Color.RED)
                            .setAction(getString(android.R.string.cancel)) {
                                toast(getString(R.string.action_cancel))
                            }
                }
            }

            snackBar.show()
        }
    }
}