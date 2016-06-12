package com.bookislife.koin.extension

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

/**
 * Created by SidneyXu on 2016/01/13.
 */
fun Context.preferencesEditor(vararg pairs: Pair<String, Any>?): SharedPreferences.Editor {
    val editor = PreferenceManager.getDefaultSharedPreferences(this).edit()
    if (pairs.isEmpty())
        return editor
    val puts = pairs.map { pair ->
        if (pair == null)
            return@map 0
        when (pair.second) {
            is String -> editor.putString(pair.first, pair.second as String)
            is Int -> editor.putInt(pair.first, pair.second as Int)
            is Long -> editor.putLong(pair.first, pair.second as Long)
            is Float -> editor.putFloat(pair.first, pair.second as Float)
            is Boolean -> editor.putBoolean(pair.first, pair.second as Boolean)
            else -> throw IllegalArgumentException("Unsupported type of ${pair.second.javaClass}")
        }
        return@map 1
    }.reduce { n1, n2 -> n1 + n2 }
    if (puts > 1) {
        editor.apply()
    }
    return editor
}

fun SharedPreferences.Editor.clearAndApply() {
    clear()
    apply()
}

fun SharedPreferences.Editor.removeAndApply(key: String) {
    remove(key)
    apply()
}

fun SharedPreferences.mkString(): String {
    return all.toString()
}

inline fun Context.preferences(init: SharedPreferences.() -> Unit): SharedPreferences {
    val prefs = PreferenceManager.getDefaultSharedPreferences(this)
    prefs.init()
    return prefs
}

fun Context.preferences(): SharedPreferences = preferences { }