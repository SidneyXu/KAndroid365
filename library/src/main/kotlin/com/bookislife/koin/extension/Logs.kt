package com.bookislife.koin.extension

import android.content.Context
import android.util.Log
import android.widget.Toast

/**
 * Created by SidneyXu on 2016/01/22.
 */
fun Context.w(msg: String) {
    if (Log.isLoggable(javaClass.simpleName, Log.WARN)) {
        Log.w(javaClass.simpleName, msg)
    }
}

fun Context.i(msg: String) {
    if (Log.isLoggable(javaClass.simpleName, Log.INFO)) {
        Log.i(javaClass.simpleName, msg)
    }
}

fun Context.t(msg: String) {
    if (Log.isLoggable(javaClass.simpleName, Log.INFO)) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
