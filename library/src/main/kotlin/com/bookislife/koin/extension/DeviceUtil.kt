package com.bookislife.koin.extension

import android.content.Context

/**
 * Created by Sidney on 2016/8/17.
 */
object DeviceUtil {
    fun isTablet(ctx: Context) = ctx.resources.configuration.smallestScreenWidthDp >= 600
}