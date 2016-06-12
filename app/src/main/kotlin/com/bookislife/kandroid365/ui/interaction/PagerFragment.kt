package com.bookislife.kandroid365.ui.interaction

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bookislife.kandroid365.R
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.UI

/**
 * Created by SidneyXu on 2016/05/15.
 */
class PagerFragment : Fragment() {

    companion object {
        val TITLE = "title"

        fun newInstance(title: String): PagerFragment {
            val fragment = PagerFragment()
            fragment.arguments = Bundle().apply {
                putString(TITLE, title)
            }
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val title = arguments.getString(TITLE)
        return UI {
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
                button(title) {
                    textColor = ContextCompat.getColor(this@PagerFragment.context, R.color.textColorPrimary)
                }.lparams {
                    below(android.R.id.edit)
                    centerInParent()
                }

            }
        }.view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        async() {
            Thread.sleep(5000)
            uiThread {
                view.find<TextView>(android.R.id.text1).apply {
                    text = "Result after long time task"
                    textColor = Color.BLUE
                }
            }
        }
    }

}