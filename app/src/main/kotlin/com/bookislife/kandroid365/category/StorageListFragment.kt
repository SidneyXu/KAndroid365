package com.bookislife.kandroid365.category

import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.SimpleListFragment
import com.bookislife.kandroid365.ui.storage.FileActivity

/**
 * Created by SidneyXu on 2016/05/24.
 */
class StorageListFragment : SimpleListFragment() {
    override val activities: List<Class<out BaseActivity>>
        get() = listOf(
                FileActivity::class.java
        )
}