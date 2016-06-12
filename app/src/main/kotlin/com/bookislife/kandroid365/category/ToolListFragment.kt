package com.bookislife.kandroid365.category

import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.SimpleListFragment
import com.bookislife.kandroid365.ui.tool.EventBusActivity
import com.bookislife.kandroid365.ui.tool.PingyinActivity
import com.bookislife.kandroid365.ui.tool.RealmActivity
import com.bookislife.kandroid365.ui.tool.RxActivity

/**
 * Created by SidneyXu on 2016/05/26.
 */
class ToolListFragment : SimpleListFragment() {
    override val activities: List<Class<out BaseActivity>>
        get() = listOf(
                EventBusActivity::class.java,
                PingyinActivity::class.java,
                RealmActivity::class.java,
                RxActivity::class.java
        )
}