package com.bookislife.koin.extension

import android.content.Context
import android.graphics.Typeface
import android.widget.TextView

/**
 * Created by SidneyXu on 2016/05/20.
 */
class Typefaces(val ctx: Context, val name: String) {

    val typeface by lazy {
        Typeface.createFromAsset(ctx.assets, name)
    }

    fun apply(vararg textViews: TextView) {
        textViews.forEach { it.typeface = typeface }
    }

}

