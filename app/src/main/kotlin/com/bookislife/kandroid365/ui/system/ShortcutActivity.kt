package com.bookislife.kandroid365.ui.system

import android.content.Intent
import android.os.Bundle
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.MainActivity
import com.bookislife.kandroid365.R

/**
 * Shortcut Showcase
 *
 * Created by SidneyXu on 2016/05/30.
 */
class ShortcutActivity : BaseActivity() {

    /**
     * Permission com.android.launcher.permission.INSTALL_SHORTCUT is needed
     *
     * Target activity must be exported
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val icon = Intent.ShortcutIconResource.fromContext(this@ShortcutActivity, R.drawable.ic_component)
        val intent = Intent(applicationContext, MainActivity::class.java)
        val shortcutIntent = Intent("com.android.launcher.action.INSTALL_SHORTCUT").apply {
            putExtra(Intent.EXTRA_SHORTCUT_NAME, getString(R.string.shortcut))
            putExtra("duplicate", false)
            putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon)
            putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent)
        }
        sendBroadcast(shortcutIntent)
        finish()
    }
}