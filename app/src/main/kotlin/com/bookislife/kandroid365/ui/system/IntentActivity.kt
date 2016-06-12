package com.bookislife.kandroid365.ui.system

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Patterns
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R
import com.bookislife.kandroid365.extension.show
import com.bookislife.koin.extension.startIntent
import kotlinx.android.synthetic.main.activity_simple.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.textResource
import java.io.File
import java.io.FileOutputStream

/**
 * Intent Showcase
 *
 * https://developer.android.com/intl/ja/guide/components/intents-common.html
 *
 * Created by SidneyXu on 2016/04/19.
 */
class IntentActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple)
        setUpToolbar()

        button1.show()
        button1.textResource = R.string.send_email
        button1.onClick {
            val sourceEmails = arrayOf(
                    "foobar@gmail.com", "illegal_format@gmailcom", "bar@abc.def"
            )
            val emails = sourceEmails.filter {
                Patterns.EMAIL_ADDRESS.matcher(it).matches()
            }

            startIntent {
                action = Intent.ACTION_SENDTO
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, emails.toTypedArray())
                putExtra(Intent.EXTRA_SUBJECT, "any subject")
                putExtra(Intent.EXTRA_TEXT, "any content")
            }
        }

        button2.show()
        button2.textResource = R.string.install_apk
        button2.onClick {
            startIntent {
                action = Intent.ACTION_VIEW
                flags = Intent.FLAG_ACTIVITY_NEW_TASK

                val file = File(Environment.getExternalStorageDirectory(), "playground.apk")
                if (!file.exists()) {
                    FileOutputStream(file).use {
                        assets.open("playground.apk").copyTo(it)
                    }
                }
                setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive")
            }
        }

    }
}