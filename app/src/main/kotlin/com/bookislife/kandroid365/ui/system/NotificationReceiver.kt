package com.bookislife.kandroid365.ui.system

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import org.jetbrains.anko.toast

/**
 * Created by SidneyXu on 2016/05/23.
 */
class NotificationReceiver : BroadcastReceiver() {

    companion object {
        val CLICK_NOTIFICATION = "click_notification"
        val DELETE_NOTIFICATION = "delete_notification"
        val ACTION_PLAY = "action_play"
        val ACTION_PAUSE = "action_pause"
    }

    override fun onReceive(ctx: Context, intent: Intent) {
        when (intent.action) {
            CLICK_NOTIFICATION -> ctx.toast("Click Notification")
            DELETE_NOTIFICATION -> ctx.toast("Delete Notification")
            ACTION_PLAY -> ctx.toast("Play music")
            ACTION_PAUSE -> ctx.toast("Pause music")
        }
    }

}