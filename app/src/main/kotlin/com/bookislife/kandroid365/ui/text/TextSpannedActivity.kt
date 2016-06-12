package com.bookislife.kandroid365.ui.text

import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R
import kotlinx.android.synthetic.main.activity_text_spanned.*

/**
 * SpannedText Showcase
 *
 * Created by SidneyXu on 2016/05/15.
 */
class TextSpannedActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_spanned)
        setUpToolbar()

        tvHtml.text = Html.fromHtml("""A html <font color="#00BBEE"> sample.""")

        val text = "Send to siriuseddy@gmail.com or access http://git.bookislife.com"

        tvLink.text = text
        Linkify.addLinks(tvLink, Linkify.EMAIL_ADDRESSES or Linkify.WEB_URLS)

        tvAutoLink.text = text

        tvMovement.text = text
        tvMovement.movementMethod = LinkMovementMethod.getInstance()

    }
}