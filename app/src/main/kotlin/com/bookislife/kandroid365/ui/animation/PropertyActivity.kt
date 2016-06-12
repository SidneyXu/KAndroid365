package com.bookislife.kandroid365.ui.animation

import android.animation.*
import android.os.Bundle
import android.view.animation.AccelerateInterpolator
import android.view.animation.BounceInterpolator
import android.view.animation.LinearInterpolator
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R
import com.bookislife.kandroid365.extension.newSpinnerAdapter
import kotlinx.android.synthetic.main.activity_property.*
import org.jetbrains.anko.onClick

/**
 * Property Animation Showcase
 *
 * Created by SidneyXu on 2016/06/10.
 */
class PropertyActivity : BaseActivity() {

    val type = arrayOf(
            "ObjectAnimator",
            "PropertyValuesHolder",
            "Keyframe",
            "Xml",
            "ValueAnimator",
            "Custom",
            "ViewPropertyAnimator"
    )

    val origin: Pair<Float, Float> by lazy {
        Pair(image.x, image.y)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property)
        setUpToolbar()

        val adapter = newSpinnerAdapter(type)
        spType.adapter = adapter

        btnShow.onClick {
            when (spType.selectedItemPosition) {
                0 -> loadObjectAnimator()
                1 -> loadPropertyValuesHolder()
                2 -> loadKeyframe()
                3 -> loadXml()
                4 -> loadValueAnimator()
                5 -> loadCustom()
                6 -> loadViewPropertyAnimator()
            }
        }

        btnReset.onClick {
            image.x = origin.first
            image.y = origin.second
            image.scaleX = 1F
            image.scaleY = 1F
            image.alpha = 1F
        }

    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        println(origin)
    }

    private fun loadViewPropertyAnimator() {
        image.animate()
                .alpha(0F)
                .y(100F)
                .rotationX(360F)
                .scaleX(0.5F)
                .scaleY(0.5F)
                .setDuration(1000)
                .start()
    }

    private fun loadCustom() {
        ObjectAnimator
                .ofFloat(image, "x", 0F, 500F)
                .setDuration(3000)
                .start()
        val animator = ObjectAnimator
                .ofFloat(image, "y", 0F, 500F)
                .setDuration(3000)
        animator.setEvaluator { fraction, startValue, endValue ->
            startValue as Float + 0.008F * fraction * (endValue as Float - startValue) * fraction * (endValue - startValue)
        }
        animator.start()
    }

    private fun loadXml() {
        val animator = AnimatorInflater.loadAnimator(this, R.animator.animator_set)
        animator.setTarget(image)
        animator.start()
    }

    private fun loadValueAnimator() {
        val valueAnimator = ValueAnimator.ofFloat(0F, 500F)
        valueAnimator.duration = 3000
        valueAnimator.interpolator = AccelerateInterpolator()
        valueAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            image.x = value
            image.y = value * value * 0.008F
        }
        valueAnimator.setTarget(image)
        valueAnimator.start()
    }

    private fun loadKeyframe() {
        val k1 = Keyframe.ofFloat(0F, 0F)
        k1.interpolator = LinearInterpolator()

        val k2 = Keyframe.ofFloat(0.5F, 400F)
        k2.interpolator = BounceInterpolator()

        val k3 = Keyframe.ofFloat(1F, 800F)
        k3.interpolator = BounceInterpolator()

        val holder = PropertyValuesHolder.ofKeyframe("y", k1, k2, k3)
        ObjectAnimator
                .ofPropertyValuesHolder(image, holder)
                .setDuration(3000)
                .start()
    }


    private fun loadPropertyValuesHolder() {
        val x = PropertyValuesHolder.ofFloat("x", 0F, 250F)
        val scaleX = PropertyValuesHolder.ofFloat("scaleX", 1F, 0.5F)
        val y = PropertyValuesHolder.ofFloat("y", 0F, 250F)
        val scaleY = PropertyValuesHolder.ofFloat("scaleY", 1F, 0.5F)
        val rotationY = PropertyValuesHolder.ofFloat("rotationY", 1F, 360F)
        ObjectAnimator
                .ofPropertyValuesHolder(image, x, y, scaleX, scaleY, rotationY)
                .setDuration(3000)
                .start()
    }

    private fun loadObjectAnimator() {
        ObjectAnimator.ofFloat(image, "rotationX", 0F, 360F)
                .setDuration(3000)
                .start()
        ObjectAnimator.ofFloat(image, "rotationY", 0F, 360F)
                .setDuration(3000)
                .start()
    }
}