package com.bookislife.kandroid365.ui.animation

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R
import kotlinx.android.synthetic.main.activity_drawable.*
import org.jetbrains.anko.onClick

/**
 * Drawable Animation Showcase
 *
 * Created by SidneyXu on 2016/05/24.
 */
class DrawableActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawable)
        setUpToolbar()

        btnShowXml.onClick {
            image1.setBackgroundResource(R.drawable.fighter)
            val animation = image1.background as AnimationDrawable
            animation.start()
        }

        btnShow.onClick {
            val interval = 100
            val animation = AnimationDrawable()
            animation.addFrame(ResourcesCompat.getDrawable(resources, R.drawable.fighter_0, null), interval)
            animation.addFrame(ResourcesCompat.getDrawable(resources, R.drawable.fighter_1, null), interval)
            animation.addFrame(ResourcesCompat.getDrawable(resources, R.drawable.fighter_2, null), interval)
            animation.addFrame(ResourcesCompat.getDrawable(resources, R.drawable.fighter_3, null), interval)
            animation.addFrame(ResourcesCompat.getDrawable(resources, R.drawable.fighter_4, null), interval)
            animation.addFrame(ResourcesCompat.getDrawable(resources, R.drawable.fighter_5, null), interval)
            animation.addFrame(ResourcesCompat.getDrawable(resources, R.drawable.fighter_6, null), interval)
            animation.addFrame(ResourcesCompat.getDrawable(resources, R.drawable.fighter_7, null), interval)
            animation.addFrame(ResourcesCompat.getDrawable(resources, R.drawable.fighter_8, null), interval)
            animation.addFrame(ResourcesCompat.getDrawable(resources, R.drawable.fighter_9, null), interval)
            animation.addFrame(ResourcesCompat.getDrawable(resources, R.drawable.fighter_10, null), interval)
            animation.addFrame(ResourcesCompat.getDrawable(resources, R.drawable.fighter_11, null), interval)
            animation.addFrame(ResourcesCompat.getDrawable(resources, R.drawable.fighter_12, null), interval)
            animation.addFrame(ResourcesCompat.getDrawable(resources, R.drawable.fighter_13, null), interval)
            animation.addFrame(ResourcesCompat.getDrawable(resources, R.drawable.fighter_14, null), interval)
            animation.addFrame(ResourcesCompat.getDrawable(resources, R.drawable.fighter_15, null), interval)
            animation.addFrame(ResourcesCompat.getDrawable(resources, R.drawable.fighter_16, null), interval)
            animation.addFrame(ResourcesCompat.getDrawable(resources, R.drawable.fighter_17, null), interval)
            animation.addFrame(ResourcesCompat.getDrawable(resources, R.drawable.fighter_18, null), interval)
            animation.addFrame(ResourcesCompat.getDrawable(resources, R.drawable.fighter_19, null), interval)
            animation.addFrame(ResourcesCompat.getDrawable(resources, R.drawable.fighter_20, null), interval)
            animation.addFrame(ResourcesCompat.getDrawable(resources, R.drawable.fighter_21, null), interval)
            animation.addFrame(ResourcesCompat.getDrawable(resources, R.drawable.fighter_22, null), interval)
            animation.addFrame(ResourcesCompat.getDrawable(resources, R.drawable.fighter_23, null), interval)
            animation.addFrame(ResourcesCompat.getDrawable(resources, R.drawable.fighter_24, null), interval)
            animation.addFrame(ResourcesCompat.getDrawable(resources, R.drawable.fighter_25, null), interval)
            animation.addFrame(ResourcesCompat.getDrawable(resources, R.drawable.fighter_26, null), interval)
            animation.addFrame(ResourcesCompat.getDrawable(resources, R.drawable.fighter_27, null), interval)
            animation.addFrame(ResourcesCompat.getDrawable(resources, R.drawable.fighter_28, null), interval)
            animation.addFrame(ResourcesCompat.getDrawable(resources, R.drawable.fighter_29, null), interval)
            image1.background = animation
            animation.start()
        }

    }
}