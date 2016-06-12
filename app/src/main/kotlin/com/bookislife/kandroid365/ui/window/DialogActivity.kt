package com.bookislife.kandroid365.ui.window

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.util.TypedValue
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R
import kotlinx.android.synthetic.main.activity_dialog.*
import org.jetbrains.anko.*

/**
 * Dialog Showcase
 *
 * Created by SidneyXu on 2016/05/16.
 */
class DialogActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)
        setUpToolbar()

        val ok = getString(android.R.string.ok)
        val cancel = getString(android.R.string.cancel)

        btnAlert.onClick {
            alert {
                title("Title")
                message("Any Content")
                positiveButton(ok) {
                    toast("Click OK")
                }
                negativeButton(cancel) {
                    toast("Click Cancel")
                }
            }.show()
        }

        btnConfirm.onClick {
            var dialog: AlertDialog? = null
            dialog = AlertDialog.Builder(this)
                    .setTitle("Title")
                    .setSingleChoiceItems(arrayOf("One", "Two", "Three"), 0,
                            { dialogInterface, i ->
                                toast("select pos: $i")
                                dialog!!.getButton(AlertDialog.BUTTON_NEGATIVE).enabled = if (i == 0) true else false
                            })
                    .setPositiveButton(ok) { dialogInterface, i ->
                        toast("Click OK")
                    }
                    .setNegativeButton(cancel) { dialogInterface, i ->
                        toast("Click Cancel")
                    }.create()
            dialog.show()
        }


        btnCustomStyle.onClick {
            val alert = AlertDialog.Builder(this, R.style.AppDialog)
                    .setTitle("Title")
                    .setMessage("Any Content")
                    .setPositiveButton(ok) { dialogInterface, i ->
                        toast("Click OK")
                    }
                    .setNegativeButton(cancel) { dialogInterface, i ->
                        toast("Click Cancel")
                    }
            alert.show()
        }

        btnCustomComp.onClick {
            val paddingLeftRight = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24f, resources.displayMetrics).toInt()
            val paddingTopBottom = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16f, resources.displayMetrics).toInt()
            alert {
                val title = UI {
                    textView {
                        backgroundColor = ContextCompat.getColor(this@DialogActivity, R.color.colorPrimary)
                        textColor = ContextCompat.getColor(this@DialogActivity, R.color.textColorPrimary)
                        setPadding(paddingLeftRight, paddingTopBottom, paddingLeftRight, paddingTopBottom)
                        text = ok
                        setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
                    }
                }.view
                customTitle(title)
                message("Any Content")
                positiveButton(ok) {
                    toast("Click OK")
                }
                negativeButton(cancel) {
                    toast("Click Cancel")
                }
            }.show()
        }

    }
}