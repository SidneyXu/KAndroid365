package com.bookislife.koin.extension

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Typeface.BOLD
import android.preference.PreferenceManager
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextUtils
import android.text.format.DateUtils
import android.text.style.*
import android.view.View
import java.util.*

/**
 * Created by SidneyXu on 2016/05/20.
 */
class StyledText : SpannableStringBuilder() {

    fun append(text: CharSequence, span: Any?): StyledText {
        if (!TextUtils.isEmpty(text)) {
            append(text)
            if (span != null) {
                val length = length
                setSpan(span, length - text.length, length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        }
        return this
    }

    override fun append(text: Char): StyledText {
        super.append(text)
        return this
    }

    override fun append(text: CharSequence?): StyledText {
        if (text != null)
            super.append(text)
        return this
    }

    fun append(text: Char, span: Any?): StyledText {
        append(text)
        if (span != null) {
            val length = length
            setSpan(span, length - 1, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        return this
    }

    fun bold(text: CharSequence): StyledText {
        return append(text, StyleSpan(BOLD))
    }

    fun background(text: CharSequence, color: Int): StyledText {
        return append(text, BackgroundColorSpan(color))
    }

    fun foreground(text: CharSequence, color: Int): StyledText {
        return append(text, ForegroundColorSpan(color))
    }

    fun foreground(text: Char, color: Int): StyledText {
        return append(text, ForegroundColorSpan(color))
    }

    fun url(text: CharSequence,
            listener: View.OnClickListener): StyledText {
        return append(text, object : URLSpan(text.toString()) {

            override fun onClick(widget: View) {
                listener.onClick(widget)
            }
        })
    }

    fun url(text: CharSequence): StyledText {
        return append(text, URLSpan(text.toString()))
    }

    fun append(date: Date): StyledText {
        val time = DateUtils.getRelativeTimeSpanString(date.time, System.currentTimeMillis(),
                DateUtils.MINUTE_IN_MILLIS, DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_SHOW_YEAR or DateUtils.FORMAT_NUMERIC_DATE)
        val timeLength = time.length
        if (length > 0 && timeLength > 0
                && Character.isUpperCase(time[0])) {
            append(time.subSequence(0, 1).toString().toLowerCase(Locale.getDefault()))
            append(time.subSequence(1, timeLength))
        } else
            append(time)

        return this
    }
}

inline fun Context.styledText(init: CharSequence.() -> Unit): StyledText {
    val text = StyledText()
    return text
}