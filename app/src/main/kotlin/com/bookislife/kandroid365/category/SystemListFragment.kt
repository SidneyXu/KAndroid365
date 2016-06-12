package com.bookislife.kandroid365.category

import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.SimpleListFragment
import com.bookislife.kandroid365.ui.system.*

/**
 * Created by SidneyXu on 2016/05/13.
 */
class SystemListFragment : SimpleListFragment() {
    override val activities: List<Class<out BaseActivity>>
        get() = listOf(
                PermissionActivity::class.java,
                RootActivity::class.java,
                NotificationActivity::class.java,
                IntentActivity::class.java,
                GPSActivity::class.java,
                SensorActivity::class.java,
                ContactActivity::class.java,
                LoadActivity::class.java,
                ShortcutActivity::class.java
        )
}