package com.bookislife.kandroid365

import android.os.Bundle
import android.support.v4.app.ListFragment
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import com.bookislife.kandroid365.extension.navigateTo

/**
 * Created by SidneyXu on 12/8/15.
 */
abstract class SimpleListFragment : ListFragment() {

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listAdapter = ArrayAdapter(
                this@SimpleListFragment.context, android.R.layout.simple_list_item_1, android.R.id.text1, getNames()
        )
    }

    override fun onListItemClick(l: ListView?, v: View?, position: Int, id: Long) {
        context.navigateTo(activities[position])
    }

    protected abstract val activities: List<Class<out BaseActivity>>

    private fun getNames(): List<String> {
        return activities.map {
            it.simpleName.removeSuffix("Activity")
        }
    }

}
