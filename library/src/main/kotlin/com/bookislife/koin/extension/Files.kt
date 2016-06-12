package com.bookislife.koin.extension

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Environment
import java.io.File
import java.io.InputStream
import java.io.OutputStream

/**
 * Created by SidneyXu on 2016/01/17.
 */
fun Context.isSDCardEnabled(): Boolean {
    val hasPermission = (packageManager.checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, packageName) == PackageManager.PERMISSION_GRANTED)
    if (hasPermission && Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED)
        return Environment.getExternalStorageDirectory() != null
    return false
}
