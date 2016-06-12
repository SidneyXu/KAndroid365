package com.bookislife.kandroid365.widget

import android.app.Dialog
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialog
import android.support.design.widget.BottomSheetDialogFragment
import android.support.design.widget.CoordinatorLayout
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewTreeObserver
import com.bookislife.kandroid365.R
import com.bookislife.kandroid365.ui.component.BottomSheetAdapter
import org.jetbrains.anko.find

/**
 * Created by SidneyXu on 2016/05/17.
 */
class ModalBottomSheet(val items: List<String>, val fullHeight: Boolean = false, val layoutManger: RecyclerView.LayoutManager) : BottomSheetDialogFragment() {

    private var globalLayoutListener: ViewTreeObserver.OnGlobalLayoutListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheetDialog = BottomSheetDialog(context)
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_sheet, null)
        val recyclerView = view.find<RecyclerView>(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = layoutManger
        recyclerView.adapter = BottomSheetAdapter(items, object : BottomSheetAdapter.OnItemListener {
            override fun onItemClick(pos: Int) {
                bottomSheetDialog.dismiss()
            }
        })
        if (fullHeight) {
            globalLayoutListener = ViewTreeObserver.OnGlobalLayoutListener() {
                val parent = view.parent as View
                val behavior = BottomSheetBehavior.from(parent)
                behavior.peekHeight = view.measuredHeight
                val layoutParams = parent.layoutParams as CoordinatorLayout.LayoutParams
                layoutParams.gravity = (Gravity.TOP or Gravity.CENTER_HORIZONTAL)

                view.viewTreeObserver.removeOnGlobalLayoutListener(globalLayoutListener)
            }
            view.viewTreeObserver.addOnGlobalLayoutListener(globalLayoutListener)
        }
        bottomSheetDialog.setContentView(view)
        return bottomSheetDialog
    }
}