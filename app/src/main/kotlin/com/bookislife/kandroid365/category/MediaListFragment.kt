package com.bookislife.kandroid365.category

import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.SimpleListFragment
import com.bookislife.kandroid365.ui.media.AudioActivity
import com.bookislife.kandroid365.ui.media.CameraActivity
import com.bookislife.kandroid365.ui.media.ImageViewActivity

/**
 * Created by SidneyXu on 2016/05/23.
 */
class MediaListFragment : SimpleListFragment() {
    override val activities: List<Class<out BaseActivity>>
        get() = listOf(
                ImageViewActivity::class.java,
                AudioActivity::class.java,
                CameraActivity::class.java
        )
}