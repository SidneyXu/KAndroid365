package com.bookislife.kandroid365.ui.interaction

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R
import com.bookislife.kandroid365.extension.log
import com.bookislife.kandroid365.model.local.Labs
import kotlinx.android.synthetic.main.activity_tab.*

/**
 * FragmentPagerAdapter Showcase
 *
 * Created by SidneyXu on 2016/05/25.
 */
class TabFragmentPagerActivity : BaseActivity() {

    var adapter: Apt? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab)
        setUpToolbar()

        adapter = Apt(Labs.fruits, supportFragmentManager)
        pager.adapter = adapter
        pager.offscreenPageLimit = 3
        tabs.tabMode = TabLayout.MODE_SCROLLABLE
        tabs.setupWithViewPager(pager)
    }

    inner class Apt(val items: List<String>, val fm: FragmentManager) : FragmentPagerAdapter(fm) {

        var deleteItems: Int = -1
        var current: Fragment? = null

        override fun getItem(position: Int): Fragment? {
            return PagerFragment.newInstance(items[position])
        }

        override fun getCount(): Int = items.size

        override fun getPageTitle(position: Int): CharSequence = items[position]

        override fun getItemPosition(`object`: Any?): Int {
            if (deleteItems != -1) {
                deleteItems = -1
                return PagerAdapter.POSITION_NONE
            }
            return super.getItemPosition(`object`)
        }

        fun deleteItem(position: Int) {
            deleteItems = position
            notifyDataSetChanged()
        }

        override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
            fm.beginTransaction().remove(`object` as Fragment).commit()
            super.destroyItem(container, position, `object`)
        }

        override fun setPrimaryItem(container: ViewGroup?, position: Int, `object`: Any?) {
            super.setPrimaryItem(container, position, `object`)
            current = `object` as Fragment
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menu.add(0, 0, 0, "Delete").setIcon(android.R.drawable.ic_delete)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            0 -> {
                val position = pager.currentItem

                log("old pos $position")

                adapter?.deleteItem(position)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}