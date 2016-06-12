package com.bookislife.kandroid365.extension

import android.R
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter

/**
 * Created by SidneyXu on 2016/03/14.
 */
fun Context.navigateTo(clazz: Class<out Activity>) {
    startActivity(Intent(this, clazz))
}

fun Context.navigateTo(clazz: Class<out Activity>, init: Bundle.() -> Unit) {
    val bundle = Bundle()
    bundle.init()
    val intent = Intent(this, clazz).apply {
        putExtras(bundle)
    }
    startActivity(intent)
}

fun Activity.navigateForResult(clazz: Class<out Activity>, requestCode: Int) {
    val intent = Intent(this, clazz)
    ActivityCompat.startActivityForResult(this, intent, requestCode, null)
}

fun Activity.setResultBundle(resultCode: Int, intent: Intent, init: Bundle.() -> Unit) {
    val bundle = Bundle()
    bundle.init()
    intent.putExtras(bundle)
    setResult(resultCode, intent)
    finish()
}

fun Activity.end(clazz: Class<out Activity>) {
    navigateTo(clazz)
    finish()
}

fun Context.log(msg: String) {
    Log.d(javaClass.simpleName, msg)
}

fun <T> Context.newSpinnerAdapter(data: Array<T>): ArrayAdapter<T> {
    val apt = ArrayAdapter<T>(this, R.layout.simple_spinner_item, data)
    apt.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
    return apt
}

//fun Context.newRecyclerAdapter(data: ArrayList<String>): SimpleRecyclerAdapter {
//    return SimpleRecyclerAdapter(data)
//}

fun <T> Context.newArrayAdapter(data: List<T>): ArrayAdapter<T> {
    val apt = ArrayAdapter<T>(this, R.layout.simple_list_item_1, data)
    return apt
}

fun Context.show(vararg views: View) {
    views.forEach { it.show() }
}