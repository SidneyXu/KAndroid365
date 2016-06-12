package com.bookislife.kandroid365.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by SidneyXu on 2016/05/15.
 */
fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.INVISIBLE
}

fun ViewGroup.inflate(id: Int) = LayoutInflater.from(context).inflate(id, this, false)

fun TextView.toFloat(): Float = if (text.toString().isEmpty()) 0F else text.toString().toFloat()

fun TextView.toInt(): Int = if (text.toString().isEmpty()) 0 else text.toString().toInt()

fun TextView.toLong(): Long = if (text.toString().isEmpty()) 0 else text.toString().toLong()
