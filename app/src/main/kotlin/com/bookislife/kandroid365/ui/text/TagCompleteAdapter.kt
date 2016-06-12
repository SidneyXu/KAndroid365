package com.bookislife.kandroid365.ui.text

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Filter
import java.util.*
import java.util.regex.Pattern

/**
 * Created by SidneyXu on 2016/04/01.
 */
open class TagCompleteAdapter(val ctx: Context, res: Int, val items: List<String>) : ArrayAdapter<String>(ctx, res, items) {

    private val suggests: ArrayList<String> = arrayListOf()
    private val filter = TagFilter()

    override fun getItem(position: Int): String? {
        return suggests[position]
    }

    override fun getCount(): Int {
        return suggests.size
    }

    override fun getFilter(): Filter? {
        return filter
    }

    open fun getTextCursor(): Int {
        return 0
    }

    inner class TagFilter : Filter() {

        val pattern = Pattern.compile("#([a-zA-Z])+")
        var start = 0
        var end = 0

        override fun performFiltering(constraint: CharSequence?): FilterResults? {
            val result = FilterResults()
            if (constraint != null) {
                suggests.clear()

                val matcher = pattern.matcher(constraint)
                while (matcher.find()) {
                    if (matcher.start() < getTextCursor() && matcher.end() >= getTextCursor()) {

                        start = matcher.start()
                        end = matcher.end()

                        val tag = constraint.subSequence(matcher.start(), matcher.end())
                        for (item in items) {
                            if (item.startsWith(tag, true)) {
                                suggests.add(item)
                            }
                        }
                    }
                }
            }
            result.values = suggests
            result.count = suggests.size
            return result
        }

        override fun publishResults(charSequence: CharSequence?, result: FilterResults?) {
            if (result != null && result.count > 0) {
                notifyDataSetChanged()
            } else {
                notifyDataSetInvalidated()
            }
        }

        override fun convertResultToString(resultValue: Any): CharSequence {
            return "${resultValue.toString()} "
        }
    }

}