package com.bookislife.kandroid365.ui.system

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.v7.app.NotificationCompat
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RemoteViews
import android.widget.Spinner
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R
import kotlinx.android.synthetic.main.activity_notification.*
import org.jetbrains.anko.onClick
import kotlin.concurrent.thread

/**
 * Notification Showcase
 *
 * Created by SidneyXu on 2016/05/18.
 */
class NotificationActivity : BaseActivity() {

    val manager: NotificationManager by lazy {
        return@lazy getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    val receiver: NotificationReceiver by lazy {
        return@lazy NotificationReceiver()
    }

    val types = arrayOf(
            "Base Notification",
            "Expanded Notification",
            "Update Notification",
            "Big Notification",
            "Progress Notification"
    )

    val flags = arrayOf(
            "Default",
            "FLAG_AUTO_CANCEL",
            "FLAG_NO_CLEAR",
            "FLAG_ONGOING_EVENT",
            "FLAG_SHOW_LIGHTS",
            "FLAG_INSISTENT"
    )

    val updateId = System.currentTimeMillis().toInt()

    var messageCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        setUpToolbar()

        setUpAdapter(spinner, types)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                spFlag.visibility = if (position == 0) {
                    View.GONE
                } else {
                    View.VISIBLE
                }
            }
        }
        setUpAdapter(spFlag, flags)

        btnShow.onClick {
            val pos = spinner.selectedItemPosition
            when (pos) {
                0 -> baseNotification()
                1 -> expandedNotification()
                2 -> updateNotification()
                3 -> bigNotification()
                4 -> progressNotification()
            }
        }

        btnClear.onClick {
            manager.cancelAll()
        }
    }

    fun setUpAdapter(sp: Spinner, array: Array<String>) {
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sp.adapter = adapter
    }

    override fun onResume() {
        super.onResume()

        val intentFilter = IntentFilter()
        intentFilter.addAction(NotificationReceiver.CLICK_NOTIFICATION)
        intentFilter.addAction(NotificationReceiver.DELETE_NOTIFICATION)
        intentFilter.addAction(NotificationReceiver.ACTION_PLAY)
        intentFilter.addAction(NotificationReceiver.ACTION_PAUSE)
        registerReceiver(receiver, intentFilter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver)
    }

    private fun baseNotification() {
        val intent = Intent(Intent.ACTION_VIEW,
                Uri.parse("http://developer.android.com/reference/android/app/Notification.html"))
        val contentIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val deleteIntent = PendingIntent.getBroadcast(this, 0, Intent(NotificationReceiver.DELETE_NOTIFICATION), PendingIntent.FLAG_CANCEL_CURRENT)

        val notification = NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(contentIntent)
                .setDeleteIntent(deleteIntent)
                .setAutoCancel(cbCancel.isChecked)
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))
                .setContentTitle("BasicNotifications Sample")
                .setContentText("Time to learn about notifications!")
                .setSubText("Tap to view documentation about notifications.")
                .build()

        when (spFlag.selectedItemPosition) {
            1 -> notification.flags = Notification.FLAG_AUTO_CANCEL
            2 -> notification.flags = Notification.FLAG_NO_CLEAR
            3 -> notification.flags = Notification.FLAG_ONGOING_EVENT
            4 -> notification.flags = Notification.FLAG_SHOW_LIGHTS
            5 -> notification.flags = Notification.FLAG_INSISTENT
        }

        show(notification)
    }

    private fun expandedNotification() {
        val notification = NotificationCompat.Builder(this)
                .setTicker("I'm a custom notification.")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(cbCancel.isChecked)
                .build()

        val remoteView = RemoteViews(packageName, R.layout.item_notification)
        remoteView.setTextViewText(R.id.textView, "Collapsed Notification")
        notification.contentView = remoteView
        if (Build.VERSION.SDK_INT > 16) {
            val expandedView = RemoteViews(packageName, R.layout.item_notification_expanded)
            notification.bigContentView = expandedView
        }
        show(notification)
    }

    private fun updateNotification() {
        val pi = PendingIntent.getBroadcast(this, 0, Intent("Update Notification"), PendingIntent.FLAG_NO_CREATE)
        val notification: Notification = if (pi == null) {
            NotificationCompat.Builder(this)
                    .setTicker("I'm a custom notification.")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentIntent(pi)
                    .setContentTitle("New Message")
                    .setContentText("You've received new message.")
                    .setAutoCancel(cbCancel.isChecked)
                    .build()
        } else {
            messageCount++
            val npi = PendingIntent.getBroadcast(this, 0, Intent("Update Notification"), PendingIntent.FLAG_UPDATE_CURRENT)
            NotificationCompat.Builder(this)
                    .setTicker("I'm a custom notification.")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentIntent(npi)
                    .setContentTitle("New Message")
                    .setContentText("Many Messages")
                    .setNumber(messageCount)
                    .setAutoCancel(cbCancel.isChecked)
                    .build()
        }

        show(notification, updateId)
    }

    private fun bigNotification() {
        val playActionIntent = PendingIntent.getBroadcast(this, 0, Intent(NotificationReceiver.ACTION_PLAY), PendingIntent.FLAG_CANCEL_CURRENT)
        val pauseActionIntent = PendingIntent.getBroadcast(this, 0, Intent(NotificationReceiver.ACTION_PAUSE), PendingIntent.FLAG_CANCEL_CURRENT)

        val notification = NotificationCompat.Builder(this)
                .setTicker("I'm a custom notification.")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(cbCancel.isChecked)
                .setContentTitle("BigNotifications Sample")
                .setContentText("Time to learn about notifications!")
                .setStyle(android.support.v4.app.NotificationCompat.BigTextStyle().bigText("Big Message"))
                .addAction(android.R.drawable.ic_media_play, "Play", playActionIntent)
                .addAction(android.R.drawable.ic_media_pause, "Pause", pauseActionIntent)
                .build()

        show(notification)
    }

    private fun progressNotification() {
        val builder = NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.ic_notification_overlay)
                .setContentTitle("Downloading...")
                .setContentText("In Progress...")

        val id = show(builder.build())
        thread {
            for (i in 1..100 step 5) {
                builder.setProgress(100, i, false)
                show(builder.build(), id)
                Thread.sleep(2000)
            }
            builder.setContentText("Download complete")
                    .setProgress(0, 0, false)
            show(builder.build(), id)
        }

    }

    fun show(notification: Notification): Int {
        val id = System.currentTimeMillis().toInt()
        manager.notify(id, notification)
        return id
    }

    fun show(notification: Notification, id: Int): Int {
        manager.notify(id, notification)
        return id
    }
}

