package com.bookislife.kandroid365.ui.component

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bookislife.kandroid365.R
import com.bookislife.kandroid365.extension.inflate
import org.jetbrains.anko.find
import org.jetbrains.anko.onClick

/**
 * Created by SidneyXu on 2016/05/17.
 */
class BottomSheetAdapter(val items: List<String>, val listener: OnItemListener) : RecyclerView.Adapter<BottomSheetAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_bottom_sheet))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = items[position];
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textView: TextView = itemView.find<TextView>(R.id.textView)

        init {
            itemView.onClick {
                listener.onItemClick(layoutPosition)
            }
        }
    }

    interface OnItemListener {
        fun onItemClick(pos: Int)
    }
}