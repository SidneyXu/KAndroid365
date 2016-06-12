package com.bookislife.kandroid365.ui.animation

import android.os.Bundle
import android.view.View
import android.view.animation.*
import android.widget.AdapterView
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R
import com.bookislife.kandroid365.extension.newSpinnerAdapter
import com.bookislife.kandroid365.extension.toFloat
import com.bookislife.kandroid365.extension.toInt
import com.bookislife.kandroid365.extension.toLong
import kotlinx.android.synthetic.main.activity_tween.*
import org.jetbrains.anko.onClick

/**
 * Tween Showcase
 *
 * Created by SidneyXu on 2016/05/30.
 */
class TweenActivity : BaseActivity() {

    val type = arrayOf(
            "Alpha",
            "Rotate",
            "Scale",
            "Translate"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tween)
        setUpToolbar()

        val adapter = newSpinnerAdapter(type)
        spType.adapter = adapter

        spType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    0 -> {
                        show(llAlpha, true)
                        show(llRotate, false)
                        show(llScale, false)
                        show(llTranslate, false)
                    }
                    1 -> {
                        show(llAlpha, false)
                        show(llRotate, true)
                        show(llScale, false)
                        show(llTranslate, false)
                    }
                    2 -> {
                        show(llAlpha, false)
                        show(llRotate, false)
                        show(llScale, true)
                        show(llTranslate, false)
                    }
                    3 -> {
                        show(llAlpha, false)
                        show(llRotate, false)
                        show(llScale, false)
                        show(llTranslate, true)
                    }
                }
            }

        }

        btnShowXml.onClick {
            when (spType.selectedItemPosition) {
                0 -> loadAlphaXmlAnimation()
                1 -> loadRotateXmlAnimation()
                2 -> loadScaleXmlAnimation()
                else -> loadTranslateXmlAnimation()
            }
        }
        btnShow.onClick {
            when (spType.selectedItemPosition) {
                0 -> loadAlphaAnimation()
                1 -> loadRotateAnimation()
                2 -> loadScaleAnimation()
                3 -> loadTranslateAnimation()
            }
        }

    }

    private fun show(v: View, visible: Boolean) {
        if (visible)
            v.visibility = View.VISIBLE
        else
            v.visibility = View.GONE
    }

    private fun apply(animation: Animation) {
        if (cbRepeat.isChecked) {
            animation.repeatCount = edRepeatCount.toInt()
        }
        animation.fillBefore = cbFillBefore.isChecked
        animation.fillAfter = cbFillAfter.isChecked
        animation.duration = edDuration.toLong()
    }

    private fun loadAlphaXmlAnimation() {
        val alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.simple_alpha)
        image.startAnimation(alphaAnimation)
    }

    private fun loadAlphaAnimation() {
        val animation = AlphaAnimation(edAlphaFrom.toFloat(), edAlphaTo.toFloat())
        apply(animation)
        image.startAnimation(animation)
    }


    private fun loadScaleXmlAnimation() {
        val scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.simple_scale)
        image.startAnimation(scaleAnimation)
    }

    private fun loadScaleAnimation() {
        val animation = ScaleAnimation(edScaleFromX.toFloat(), edScaleToX.toFloat(), edScaleFromY.toFloat(), edScaleToY.toFloat(),
                edScalePivotx.toFloat() * image.width / 100, edScalePivoty.toFloat() * image.height / 100)
        apply(animation)
        image.startAnimation(animation)
    }

    private fun loadTranslateXmlAnimation() {
        val scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.simple_translate)
        image.startAnimation(scaleAnimation)
    }

    private fun loadTranslateAnimation() {
        val animation = TranslateAnimation(edTranslateFromX.toFloat(), edTranslateToX.toFloat(), edTranslateFromY.toFloat(), edTranslateToY.toFloat())
        apply(animation)
        image.startAnimation(animation)
    }

    private fun loadRotateXmlAnimation() {
        val scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.simple_rotate)
        image.startAnimation(scaleAnimation)
    }

    private fun loadRotateAnimation() {
        println("width=${image.width}, height=${image.height}")
        val animation = RotateAnimation(edDegressFrom.toFloat(), edDegressTo.toFloat(),
                edPivotx.toFloat() * image.width / 100, edPivoty.toFloat() * image.height / 100)
        apply(animation)
        image.startAnimation(animation)
    }

}