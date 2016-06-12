package com.bookislife.kandroid365.category

import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.SimpleListFragment
import com.bookislife.kandroid365.ui.text.*

/**
 * Created by SidneyXu on 2016/05/10.
 */
class TextListFragment : SimpleListFragment() {
    override val activities: List<Class<out BaseActivity>>
        get() = listOf(
                TextInputActivity::class.java,
                TextSwitcherActivity::class.java,
                TextSpannedActivity::class.java,
                AutoCompleteTextActivity::class.java,
                TextStyleActivity::class.java
        )
}