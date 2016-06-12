package com.bookislife.kandroid365.category

import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.SimpleListFragment
import com.bookislife.kandroid365.ui.animation.*

/**
 * Created by SidneyXu on 2016/05/27.
 */
class AnimationListFragment : SimpleListFragment() {
    override val activities: List<Class<out BaseActivity>>
        get() = listOf(
                DrawableActivity::class.java,
                InterceptorActivity::class.java,
                TweenActivity::class.java,
                PropertyActivity::class.java,
                LayoutActivity::class.java
        )
}