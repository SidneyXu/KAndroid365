package com.bookislife.kandroid365.ui.tool

import android.os.Bundle
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R
import com.bookislife.kandroid365.extension.show
import kotlinx.android.synthetic.main.activity_simple.*
import net.sourceforge.pinyin4j.PinyinHelper
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType
import org.jetbrains.anko.onClick

/**
 * Pingyin Showcase
 *
 * Created by SidneyXu on 2016/05/27.
 */
class PingyinActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple)
        setUpToolbar()

        text1.show()
        text2.show()
        text1.text = "曹操 在 操场playground做操"
        button1.show()
        button1.text = getString(R.string.show)
        button1.onClick {
            val format = HanyuPinyinOutputFormat().apply {
                toneType = HanyuPinyinToneType.WITH_TONE_MARK
                vCharType = HanyuPinyinVCharType.WITH_U_UNICODE
            }

            text2.text = text1.text.map {
                val array = PinyinHelper.toHanyuPinyinStringArray(it, format)
                if (array == null) {
                    it
                } else {
                    array[0]
                }
            }.joinToString()
        }
    }
}