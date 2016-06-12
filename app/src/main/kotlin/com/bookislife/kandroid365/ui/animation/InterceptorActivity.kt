package com.bookislife.kandroid365.ui.animation

import android.os.Bundle
import android.view.animation.*
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R
import com.bookislife.kandroid365.extension.newSpinnerAdapter
import kotlinx.android.synthetic.main.activity_interceptor.*
import org.jetbrains.anko.onClick

/**
 * Interpolator Showcase
 *
 * Created by SidneyXu on 2016/05/27.
 */
class InterceptorActivity : BaseActivity() {

    val type = arrayOf(
            "LinearInterpolator",
            "AccelerateInterpolator",
            "DecelerateInterpolator",
            "AccelerateDecelerateInterpolator",
            "BounceInterpolator",
            "AnticipateInterpolator",
            "AnticipateOvershootInterpolator",
            "Custom Interpolator"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interceptor)
        setUpToolbar()

        val adapter = newSpinnerAdapter(type)
        spType.adapter = adapter

        btnShow.onClick {
            val animation = loadAnimation()
            animation.interpolator = when (spType.selectedItemPosition) {
                1 -> AccelerateInterpolator()
                2 -> DecelerateInterpolator()
                3 -> AccelerateDecelerateInterpolator()
                4 -> BounceInterpolator()
                5 -> AnticipateInterpolator()
                6 -> AnticipateOvershootInterpolator()
                7 -> GravityInterpolator()
                else -> LinearInterpolator()
            }
            image1.startAnimation(animation)
        }
    }

    private fun loadAnimation(): Animation {
        val animation = AnimationUtils.loadAnimation(this, R.anim.translate)
        animation.duration = 2000
        animation.fillAfter = true
        return animation
    }

    class GravityInterpolator : Interpolator {
        override fun getInterpolation(input: Float): Float {
            var f: Float = 0F
            if (input < 0.5f) {
                f = 4 * input - 1f
                return 1f - f * f
            } else {
                f = input - 0.5f
                f = (4 * f - 1f)
                return (1f - f * f) * 0.4f
            }
        }

    }
}