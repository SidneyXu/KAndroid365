package com.bookislife.kandroid365.ui.text

import android.os.Bundle
import android.widget.ArrayAdapter
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R
import com.bookislife.kandroid365.model.local.Labs
import kotlinx.android.synthetic.main.activity_auto_complete_text.*

/**
 * AutoCompleteTextView Showcase
 *
 * Created by SidneyXu on 2016/05/17.
 */
class AutoCompleteTextActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_complete_text)
        setUpToolbar()

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, Labs.words)
        actv1.setAdapter(adapter)

        val tagAdapter = object : TagCompleteAdapter(this, android.R.layout.simple_dropdown_item_1line, Labs.wordsTagPrefix) {
            override fun getTextCursor(): Int {
                return actv2.selectionStart
            }
        }
        actv2.setAdapter(tagAdapter)
    }

}