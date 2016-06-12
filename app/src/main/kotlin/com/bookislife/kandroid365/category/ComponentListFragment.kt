package com.bookislife.kandroid365.category

import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.SimpleListFragment
import com.bookislife.kandroid365.ui.component.*

/**
 * Created by SidneyXu on 2016/05/12.
 */
class ComponentListFragment : SimpleListFragment() {

    override val activities: List<Class<out BaseActivity>>
        get() = listOf(
                PercentActivity::class.java,
                SearchActivity::class.java,
                SearchInfoActivity::class.java,
                ButtonActivity::class.java,
                DatePickerActivity::class.java,
                ProgressActivity::class.java,
                ToastActivity::class.java,
                SnackBarActivity::class.java,
                SeekBarActivity::class.java,
                RatingActivity::class.java,
                SpinnerActivity::class.java,
                SelectionActivity::class.java,
                CardViewActivity::class.java,
                SettingsActivity::class.java,
                BottomSheetActivity::class.java,
                ActionModeActivity::class.java
        )
}