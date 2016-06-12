package com.bookislife.kandroid365.ui.component

import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.RadioButton
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R
import com.bookislife.kandroid365.widget.ModalBottomSheet
import kotlinx.android.synthetic.main.activity_bottom_sheet.*
import org.jetbrains.anko.dimen
import org.jetbrains.anko.find
import org.jetbrains.anko.onClick
import java.util.*
import kotlin.properties.Delegates

/**
 * BottomSheet Showcase
 *
 * Created by SidneyXu on 2016/05/17.
 */
class BottomSheetActivity : BaseActivity(), BottomSheetAdapter.OnItemListener {

    internal var behavior: BottomSheetBehavior<View> by Delegates.notNull<BottomSheetBehavior<View>>()
    private var apt: BottomSheetAdapter? = null
    private var displayFab = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_sheet)
        setUpToolbar()

        btnShow.onClick {
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
        btnShowDialog.onClick {
            showBottomSheetDialog()
        }

        val growAnim = AnimationUtils.loadAnimation(this, R.anim.simple_grow)
        val shrinkAnim = AnimationUtils.loadAnimation(this, R.anim.simple_shrink)
        shrinkAnim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationStart(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                fab.visibility = View.GONE
            }

        })
        growAnim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {

            }

            override fun onAnimationStart(animation: Animation?) {
                fab.visibility = View.VISIBLE
            }

        })
        fab.startAnimation(growAnim)

        behavior = BottomSheetBehavior.from<View>(bottom_sheet)

        behavior.peekHeight = dimen(R.dimen.peek_height)
        behavior.isHideable = true

        behavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                // React to state change
                when (newState) {
                    BottomSheetBehavior.STATE_DRAGGING -> {
                        if (displayFab) {
                            fab.startAnimation(shrinkAnim)
                        }
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        displayFab = true
                        fab.startAnimation(growAnim)
                    }
                    BottomSheetBehavior.STATE_EXPANDED -> displayFab = false
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                // React to dragging events
            }
        })

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        apt = BottomSheetAdapter(items, this)
        recyclerView.adapter = apt
    }

    private fun showBottomSheetDialog() {
        if (behavior.state == BottomSheetBehavior.STATE_EXPANDED) {
            behavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
        val layoutManager: RecyclerView.LayoutManager
        if (cbGrid.isChecked) {
            val count = rgColumn.find<RadioButton>(rgColumn.checkedRadioButtonId).text.toString().toInt()
            layoutManager = GridLayoutManager(this, count)
        } else {
            layoutManager = LinearLayoutManager(this)
        }

        val bottomSheetDialog = ModalBottomSheet(items, scFullHeight.isChecked, layoutManager)
        bottomSheetDialog.show(supportFragmentManager, "bottom sheet")
    }

    val items: List<String>
        get() {
            val items = ArrayList<String>()
            items.add("Item 1")
            items.add("Item 2")
            items.add("Item 3")
            items.add("Item 4")
            items.add("Item 5")
            return items
        }

    override fun onItemClick(pos: Int) {
        behavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }
}