package com.bookislife.kandroid365.ui.interaction

import android.os.Bundle
import android.support.v4.app.FragmentTabHost
import android.widget.ImageView
import android.widget.TabHost
import android.widget.TextView
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.find
import org.jetbrains.anko.imageResource

/**
 * FragmentTabHost Showcase
 *
 * Created by SidneyXu on 2016/05/15.
 */
class FragmentTabHostActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_host)
        setUpToolbar()

        val tabhost = find<FragmentTabHost>(android.R.id.tabhost)
        tabhost.setup(this, supportFragmentManager, R.id.realtabcontent)

        val tabSpec1 = createTabSpec(tabhost, R.string.text)
        val tabSpec2 = createTabSpec(tabhost, R.string.component)
        val tabSpec3 = createTabSpec(tabhost, R.string.theme)
        val tabSpec4 = createTabSpec(tabhost, R.string.net)

        tabhost.addTab(tabSpec1, PagerFragment::class.java, Bundle().apply { putString(PagerFragment.TITLE, tabSpec1.tag) })
        tabhost.addTab(tabSpec2, PagerFragment::class.java, Bundle().apply { putString(PagerFragment.TITLE, tabSpec2.tag) })
        tabhost.addTab(tabSpec3, PagerFragment::class.java, Bundle().apply { putString(PagerFragment.TITLE, tabSpec3.tag) })
        tabhost.addTab(tabSpec4, PagerFragment::class.java, Bundle().apply { putString(PagerFragment.TITLE, tabSpec4.tag) })

        tabhost.tabWidget.getChildAt(0).backgroundResource = R.drawable.tab_selectable_item
        tabhost.tabWidget.getChildAt(1).backgroundResource = R.drawable.tab_selectable_item
        tabhost.tabWidget.getChildAt(2).backgroundResource = R.drawable.tab_selectable_item
        tabhost.tabWidget.getChildAt(3).backgroundResource = R.drawable.tab_selectable_item
    }

    private fun createTabSpec(tabHost: FragmentTabHost, titleId: Int): TabHost.TabSpec {
        val tabItem = layoutInflater.inflate(R.layout.item_tab_spec, null)
        val tabText = tabItem.find<TextView>(R.id.text1)
        tabText.text = getString(titleId)
        val tabImage = tabItem.find<ImageView>(R.id.image1)
        tabImage.imageResource = when (titleId) {
            R.string.text -> R.drawable.ic_text
            R.string.component -> R.drawable.ic_component
            R.string.theme -> R.drawable.ic_theme
            else -> R.drawable.ic_net
        }
        return tabHost.newTabSpec(tabText.text.toString()).setIndicator(tabItem)
    }
}