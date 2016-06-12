package com.bookislife.kandroid365.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import com.bookislife.kandroid365.R

/**
 * Created by SidneyXu on 2016/05/24.
 */
class ClearableEditText : EditText, View.OnTouchListener, View.OnFocusChangeListener {

    private var clearTextIcon: Drawable? = null

    constructor(context: Context) : this(context, null)

    @JvmOverloads constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int = android.R.attr.autoCompleteTextViewStyle) : super(context, attrs, defStyleAttr) {
    }

    init {
        val drawable = ContextCompat.getDrawable(context, R.drawable.abc_ic_clear_mtrl_alpha)
        val wrappedDrawable = DrawableCompat.wrap(drawable)
        DrawableCompat.setTint(wrappedDrawable, currentHintTextColor)
        clearTextIcon = wrappedDrawable.apply {
            setBounds(0, 0, intrinsicHeight, intrinsicHeight)
        }
        setClearIconVisible(false)
        super.setOnTouchListener(this)
        super.setOnFocusChangeListener(this)
    }

    override fun onTextChanged(text: CharSequence, start: Int, lengthBefore: Int, lengthAfter: Int) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
        if (isFocused) {
            setClearIconVisible(text.length > 0)
        }
    }

    override fun onTouch(v: View?, event: MotionEvent): Boolean {
        clearTextIcon?.let {
            if (it.isVisible && event.x > width - paddingRight - it.intrinsicWidth) {
                if (MotionEvent.ACTION_UP == event.action) {
                    error = null
                    setText("")
                }
            }
            return@let true
        }
        return false
    }

    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        if (hasFocus) {
            setClearIconVisible(text.length > 0)
        } else {
            setClearIconVisible(false)
        }
    }

    private fun setClearIconVisible(visible: Boolean) {
        clearTextIcon?.setVisible(visible, false)
        setCompoundDrawables(
                compoundDrawables[0],
                compoundDrawables[1],
                if (visible) clearTextIcon else null,
                compoundDrawables[3])
    }

}