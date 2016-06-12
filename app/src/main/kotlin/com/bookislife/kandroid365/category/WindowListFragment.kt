package com.bookislife.kandroid365.category

import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.SimpleListFragment
import com.bookislife.kandroid365.ui.window.DialogActivity
import com.bookislife.kandroid365.ui.window.MenuActivity
import com.bookislife.kandroid365.ui.window.PopupActivity

/**
 * Created by SidneyXu on 2016/05/17.
 */
class WindowListFragment : SimpleListFragment() {

    override val activities: List<Class<out BaseActivity>>
        get() = listOf(
                DialogActivity::class.java,
                PopupActivity::class.java,
                MenuActivity::class.java
        )
}