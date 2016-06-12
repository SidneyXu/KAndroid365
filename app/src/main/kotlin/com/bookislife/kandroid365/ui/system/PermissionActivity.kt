package com.bookislife.kandroid365.ui.system

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.CalendarContract
import android.view.View
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R
import com.bookislife.kandroid365.extension.show
import kotlinx.android.synthetic.main.activity_simple.*
import org.jetbrains.anko.onClick

/**
 * Permission Showcase
 *
 * Created by SidneyXu on 2016/05/13.
 */
class PermissionActivity : BaseActivity() {

    val permissions = arrayOf(Manifest.permission.READ_CALENDAR)

    /**
     *  Runtime Permission
     *
     *  http://developer.android.com/guide/topics/security/permissions.html
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple)
        setUpToolbar()

        text1.show()
        button1.show()
        button1.text = getString(R.string.get_calendar)
        button1.onClick {
            // throw exception if using 6.0, some permission need runtime check
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val deniedPermission = permissions.firstOrNull {
                    checkSelfPermission(it) != PackageManager.PERMISSION_GRANTED
                }
                text1.text = "Permission $deniedPermission is denied"
                if (deniedPermission != null) {
                    requestPermissions(permissions, 10000)
                } else {
                    getData()
                }
            } else {
                getData()
            }
        }
    }

    fun getData() {
        val uri = CalendarContract.Calendars.CONTENT_URI
        val cursor = contentResolver.query(uri, arrayOf("name"), null, null, null)
        text1.text = buildString {
            while (cursor.moveToNext()) {
                append(cursor.getString(cursor.getColumnIndex("name")))
                append("\n")
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == 10000) {
            permissions.forEachIndexed { i, s ->
                println("permission $s is ${i == PackageManager.PERMISSION_GRANTED}")
            }
        }
    }
}