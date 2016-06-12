package com.bookislife.kandroid365.ui.component

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.SpinnerAdapter
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R
import com.bookislife.kandroid365.model.local.Labs
import kotlinx.android.synthetic.main.activity_spinner.*
import org.jetbrains.anko.*

/**
 * Spinner Showcase
 *
 * Created by SidneyXu on 2016/05/15.
 */
class SpinnerActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner)
        setUpToolbar()

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Labs.fruits)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner1.adapter = adapter
        spinner1.prompt = getString(R.string.hint_select_item)
        applyOnItemSelectedListener(spinner1)

        spinner2.adapter = adapter
        spinner2.prompt = spinner1.prompt
        applyOnItemSelectedListener(spinner2)

        val apt = Apt(this, Labs.fruits)
        spinner3.adapter = apt
        spinner3.prompt = spinner1.prompt
        applyOnItemSelectedListener(spinner3)
    }

    fun applyOnItemSelectedListener(sp: Spinner) {
        sp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            var init = false

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                // not fire event at startup
                if (!init) {
                    init = true
                    return
                }
                toast(parent.selectedItem.toString())
            }
        }
    }

    class Apt(context: Context, items: List<String>) : ArrayAdapter<String>(context, 0, items), SpinnerAdapter {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
            return context.UI {
                textView {
                    gravity = Gravity.CENTER
                    padding = dimen(R.dimen.first_line_margin)
                    textSizeDimen = R.dimen.text_size_large
                    setCompoundDrawablesWithIntrinsicBounds(0, 0, android.R.drawable.ic_menu_more, 0)
                    text = getItem(position)
                }
            }.view
        }

        override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View? {
            return context.UI {
                textView {
                    gravity = Gravity.CENTER_VERTICAL
                    padding = dimen(R.dimen.first_line_margin)
                    textSizeDimen = R.dimen.text_size_large
                    text = getItem(position)
                }
            }.view
        }
    }

}