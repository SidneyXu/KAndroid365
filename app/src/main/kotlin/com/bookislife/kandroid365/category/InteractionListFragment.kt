package com.bookislife.kandroid365.category

import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.SimpleListFragment
import com.bookislife.kandroid365.ui.interaction.FragmentTabHostActivity
import com.bookislife.kandroid365.ui.interaction.TabFragmentPagerActivity
import com.bookislife.kandroid365.ui.interaction.TabPagerActivity

/**
 * Created by SidneyXu on 2016/05/16.
 */
class InteractionListFragment : SimpleListFragment() {

    override val activities: List<Class<out BaseActivity>>
        get() = listOf(
                FragmentTabHostActivity::class.java,
                TabPagerActivity::class.java,
                TabFragmentPagerActivity::class.java
        )
}