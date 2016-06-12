package com.bookislife.kandroid365.category

import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.SimpleListFragment
import com.bookislife.kandroid365.ui.net.AdvancedWebViewActivity
import com.bookislife.kandroid365.ui.net.DownloadActivity
import com.bookislife.kandroid365.ui.net.WebViewActivity

/**
 * Created by SidneyXu on 2016/05/12.
 */
class NetListFragment : SimpleListFragment() {
    override val activities: List<Class<out BaseActivity>>
        get() = listOf(
                WebViewActivity::class.java,
                AdvancedWebViewActivity::class.java,
                DownloadActivity::class.java
        )
}