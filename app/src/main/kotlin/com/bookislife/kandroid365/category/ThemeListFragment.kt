package com.bookislife.kandroid365.category

import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.SimpleListFragment
import com.bookislife.kandroid365.ui.theme.DayNightActivity
import com.bookislife.kandroid365.ui.theme.TransparentActivity

/**
 * Created by SidneyXu on 2016/05/16.
 */
class ThemeListFragment : SimpleListFragment() {
    override val activities: List<Class<out BaseActivity>>
        get() = listOf(
                TransparentActivity::class.java,
                DayNightActivity::class.java
        )
}