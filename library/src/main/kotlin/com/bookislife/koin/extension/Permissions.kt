package com.bookislife.koin.extension

import android.app.Activity
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat

/**
 * Created by SidneyXu on 2016/11/01.
 */
object Permissions {

    fun     checkPermission(activity: Activity, requestCode: Int, permissions: Array<String>): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val deniedPermissions = permissions.filter {
                ContextCompat.checkSelfPermission(activity, it) != PackageManager.PERMISSION_GRANTED
            }
            if (deniedPermissions.isEmpty()) {
                return true
            }
            val shouldShowUI = deniedPermissions.firstOrNull {
                ActivityCompat.shouldShowRequestPermissionRationale(activity, it)
            }
            if (shouldShowUI == null) {
                ActivityCompat.requestPermissions(activity, permissions, requestCode)
            } else {
                val alert = AlertDialog.Builder(activity).apply {
                    setCancelable(true)
                    setTitle("Permission necessary")
                    setMessage("${permissions.joinToString("\n")} is required.")
                    setPositiveButton(android.R.string.yes) { dialogInterface, i ->
                        ActivityCompat.requestPermissions(activity, permissions, requestCode)
                    }
                }.create()
                alert.show()
            }
        }
        return false
    }
}