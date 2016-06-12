package com.bookislife.kandroid365.ui.text

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.TypedValue
import android.widget.SeekBar
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R
import kotlinx.android.synthetic.main.activity_text_style.*

/**
 * TextView Showcase
 *
 * Created by SidneyXu on 2016/05/24.
 */
class TextStyleActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_style)
        setUpToolbar()

        val minSize = resources.getString(R.dimen.text_size_normal).replace("sp", "").toFloat()

        tvCount.text = String.format(getString(R.string.msg_text_count), 0)
        tvSize.text = String.format(getString(R.string.msg_text_size), minSize)

        sbSize.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tvSize.text = String.format(getString(R.string.msg_text_size), minSize + progress)
                edInput.setTextSize(TypedValue.COMPLEX_UNIT_SP, minSize + progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })

        edInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                tvCount.text = String.format(getString(R.string.msg_text_count), s?.length)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }
}