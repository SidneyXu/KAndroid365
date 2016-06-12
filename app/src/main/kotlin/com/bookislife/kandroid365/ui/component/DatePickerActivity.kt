package com.bookislife.kandroid365.ui.component

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R
import kotlinx.android.synthetic.main.activity_date_picker.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.toast
import java.util.*

/**
 * DatePicker Showcase
 *
 * Created by SidneyXu on 2016/05/12.
 */
class DatePickerActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date_picker)
        setUpToolbar()

        btnDatePicker.onClick {
            val now = Calendar.getInstance()
            val dialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                toast("Your choice is $year, $monthOfYear, $dayOfMonth")
            },
                    now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH))
            dialog.show()
        }

        btnTimePicker.onClick {
            val now = Calendar.getInstance()
            val dialog = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { timePicker, hourOfDay, minute ->
                toast("Your choice is $hourOfDay, $minute")
            },
                    now.get(Calendar.HOUR), now.get(Calendar.MINUTE), true)
            dialog.show()
        }

        btnTimePicker2.onClick {
            val now = Calendar.getInstance()
            val dialog = TimePickerDialog(this, R.style.AppTimePicker, TimePickerDialog.OnTimeSetListener { timePicker, hourOfDay, minute ->
                toast("Your choice is $hourOfDay, $minute")
            },
                    now.get(Calendar.HOUR), now.get(Calendar.MINUTE), true)
            dialog.show()
        }
    }
}