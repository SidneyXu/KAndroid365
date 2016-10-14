package com.bookislife.koin.extension

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView

/**
 * Created by SidneyXu on 2016/05/12.
 */
fun TextView.setNumber(n: Number) {
    text = "" + n
}

fun TextView.toFloat(): Float = if (text.toString().isEmpty()) 0F else text.toString().toFloat()

fun TextView.toInt(): Int = if (text.toString().isEmpty()) 0 else text.toString().toInt()

fun TextView.toLong(): Long = if (text.toString().isEmpty()) 0 else text.toString().toLong()

fun ViewGroup.inflate(id: Int) = LayoutInflater.from(context).inflate(id, this, false)

fun EditText.openKeyboard(ctx: Context) {
    val input = ctx.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    input.showSoftInput(this, 0)
}

fun EditText.hideKeyboard(ctx: Context) {
    val input = ctx.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    input.hideSoftInputFromWindow(windowToken, 0)
}

fun PagerAdapter.findFragmentByPosition(viewPager: ViewPager, position: Int): Fragment {
    return instantiateItem(viewPager, position) as Fragment
}