package com.bookislife.koin.extension

import android.content.res.Resources

/**
 * Created by SidneyXu on 2016/01/14.
 */
fun Resources.dip2px(dipValue: Float): Int = (dipValue * displayMetrics.density + 0.5f).toInt()

fun Resources.px2dip(pxValue: Float): Int = (pxValue / displayMetrics.density + 0.5f).toInt()
