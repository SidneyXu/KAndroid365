package com.bookislife.kandroid365.ui.interaction

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v4.view.PagerAdapter
import android.text.InputType
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R
import com.bookislife.kandroid365.model.local.Labs
import kotlinx.android.synthetic.main.activity_tab.*
import org.jetbrains.anko.*

/**
 * TabLayout Showcase
 *
 * Created by SidneyXu on 2016/05/17.
 */
class TabPagerActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab)
        setUpToolbar()

        val adapter = Apt(Labs.fruits)
        pager.adapter = adapter
        pager.offscreenPageLimit = 3
        tabs.tabMode = TabLayout.MODE_SCROLLABLE
        tabs.setupWithViewPager(pager)
    }

    inner class Apt(val items: List<String>) : PagerAdapter() {

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view == `object`
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val view = UI {
                relativeLayout {
                    textView {
                        id = android.R.id.text1
                        text = "Recreate current view..."
                        textColor = Color.BLACK
                    }.lparams {
                        centerInParent()
                    }
                    editText {
                        id = android.R.id.edit
                        hint = "Page Count for retain"
                        inputType = InputType.TYPE_NUMBER_VARIATION_NORMAL
                    }.lparams {
                        below(android.R.id.text1)
                        centerInParent()
                    }
                    val title = Labs.fruits[position]
                    button(title) {
                        textColor = ContextCompat.getColor(this@TabPagerActivity, R.color.textColorPrimary)
                    }.lparams {
                        below(android.R.id.edit)
                        centerInParent()
                    }

                }
            }.view
            async() {
                Thread.sleep(5000)
                uiThread {
                    view.find<TextView>(android.R.id.text1).apply {
                        text = "Result after long time task"
                        textColor = Color.BLUE
                    }
                }
            }
            container.addView(view)
            return view
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }

        override fun getCount(): Int = items.size

        override fun getPageTitle(position: Int): CharSequence = items[position]

    }

}