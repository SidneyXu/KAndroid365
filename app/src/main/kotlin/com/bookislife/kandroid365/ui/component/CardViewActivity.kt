package com.bookislife.kandroid365.ui.component

import android.os.Bundle
import android.widget.SeekBar
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R
import kotlinx.android.synthetic.main.activity_cardview.*
import org.jetbrains.anko.dimen

/**
 * CardView Showcase
 *
 * Created by SidneyXu on 2016/05/17.
 */
class CardViewActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cardview)
        setUpToolbar()

        sbSize.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                card.radius = progress * dimen(R.dimen.cardview_default_radius).toFloat()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
    }
}