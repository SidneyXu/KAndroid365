package com.bookislife.kandroid365.ui.component

import android.os.Bundle
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R
import com.bookislife.kandroid365.extension.log
import kotlinx.android.synthetic.main.activity_seekbar.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.onSeekBarChangeListener
import org.jetbrains.anko.toast

/**
 * SeekBar Showcase
 *
 * Created by SidneyXu on 2016/05/13.
 */
class SeekBarActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seekbar)
        setUpToolbar()

        tvProgress1.text = ""
        tvProgress2.text = ""

        seekbar1.onSeekBarChangeListener {
            onStartTrackingTouch {
                log("start tracking")
            }
            onProgressChanged { seekBar, progress, fromUser ->
                tvProgress1.text = "progress is $progress / ${seekbar1!!.max}"
            }
        }

        seekbar2.onSeekBarChangeListener {
            onProgressChanged { seekBar, progress, fromUser ->
                log("progress is $progress, fromUser is $fromUser")
                tvProgress2.text = "progress is $progress / ${seekbar2!!.max}"
            }

            onStopTrackingTouch {
                log("stop tracking")
            }
        }
        seekbar2.thumb = getDrawable(R.drawable.ic_favorite)

        // Custom thumb must disable split track in api 21 and higher
        seekbar2.splitTrack = false

        btnSetProgress.onClick {
            seekbar1.progress = 50
            seekbar2.progress = 50
        }
    }
}