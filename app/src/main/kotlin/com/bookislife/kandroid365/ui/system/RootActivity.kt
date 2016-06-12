package com.bookislife.kandroid365.ui.system

import android.os.Bundle
import android.view.View
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R
import com.bookislife.kandroid365.extension.show
import kotlinx.android.synthetic.main.activity_simple.*
import org.jetbrains.anko.onClick

/**
 * Root Showcase
 *
 * Created by SidneyXu on 2016/05/13.
 */
class RootActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple)
        setUpToolbar()

        text1.show()
        button1.show()
        button1.text = getString(R.string.require_root)
        button1.onClick {
            val runtime: Runtime = Runtime.getRuntime()
            val process: Process = runtime.exec(arrayOf("su", "-c", "echo Hello root!"))
            val exitValue = process.waitFor()
            if (exitValue != 0) {
                text1.text = "exit with $exitValue"
                return@onClick
            }

            process.inputStream.use {
                text1.text = it.bufferedReader().readLines().joinToString()
            }
        }

    }
}