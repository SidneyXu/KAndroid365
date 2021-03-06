package com.bookislife.koin.extension

import android.app.Activity
import android.content.Context
import android.content.Intent

/**
 * Created by SidneyXu on 2016/05/20.
 */
fun Intent.startActivity(ctx: Context) = ctx.startActivity(this)

fun Context.startIntent(init: Intent.() -> Unit): Intent {
    val intent = Intent()
    intent.init()
    intent.startActivity(this)
    return intent
}

fun Activity.startIntentForResult(requestCode: Int, init: Intent.() -> Unit): Intent {
    val intent = Intent()
    intent.init()
    startActivityForResult(intent, requestCode)
    return intent
}