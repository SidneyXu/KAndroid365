package com.bookislife.kandroid365.widget

import android.content.Context
import android.text.Selection
import android.util.AttributeSet
import android.widget.AutoCompleteTextView
import com.bookislife.kandroid365.ui.text.AutoCompleteTextActivity
import com.bookislife.kandroid365.ui.text.TagCompleteAdapter

/**
 * Created by SidneyXu on 2016/05/17.
 */
class TagCompleteTextView : AutoCompleteTextView {

    constructor(context: Context) : this(context, null)

    @JvmOverloads constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int = android.R.attr.autoCompleteTextViewStyle) : super(context, attrs, defStyleAttr) {
    }

    override fun replaceText(text: CharSequence?) {
        clearComposingText()

        if (adapter is TagCompleteAdapter) {
            val filter = (adapter as TagCompleteAdapter).filter as TagCompleteAdapter.TagFilter
            val spannable = getText()
            spannable.replace(filter.start, filter.end, text)
            return
        }

        setText(text)
        // make sure we keep the caret at the end of the text view
        val spannable = getText()
        Selection.setSelection(spannable, spannable.length)
    }
}