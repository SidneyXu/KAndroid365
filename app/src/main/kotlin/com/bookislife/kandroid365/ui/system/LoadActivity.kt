package com.bookislife.kandroid365.ui.system

import android.content.Context
import android.os.Bundle
import android.support.v4.app.LoaderManager
import android.support.v4.content.AsyncTaskLoader
import android.support.v4.content.Loader
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R
import com.bookislife.kandroid365.extension.log
import com.bookislife.kandroid365.extension.show
import kotlinx.android.synthetic.main.activity_simple.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.toast

/**
 * LoaderManager Showcase
 *
 * Created by SidneyXu on 2016/05/25.
 */
class LoadActivity : BaseActivity() {

    companion object {
        val EXTRA_MESSAGE = "message"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple)
        setUpToolbar()

        button1.show()
        button1.text = getString(R.string.load)
        button1.onClick {
            val bundle = Bundle()
            bundle.putString(EXTRA_MESSAGE, "hello world")

            // same id invoke onCreateLoader() only once
            supportLoaderManager.initLoader(1, bundle, AppLoaderCallback(this))
            supportLoaderManager.initLoader(1, bundle, AppLoaderCallback(this))
            supportLoaderManager.initLoader(2, bundle, AppLoaderCallback(this))
        }
    }

    class AppLoaderCallback(val ctx: Context) : LoaderManager.LoaderCallbacks<String> {
        override fun onLoaderReset(loader: Loader<String>?) {
            ctx.log("onLoaderReset()")
        }

        override fun onCreateLoader(id: Int, args: Bundle?): Loader<String>? {
            ctx.log("onCreateLoader() id is $id")

            val loader = AppLoader(ctx, args?.getString(EXTRA_MESSAGE))
            loader.forceLoad()
            return loader
        }

        override fun onLoadFinished(loader: Loader<String>?, data: String?) {
            ctx.log("onLoadFinished() data is $data, id is ${loader?.id}")

            data?.apply {
                ctx.toast(data)
            }
        }
    }

    class AppLoader(ctx: Context, val text: String?) : AsyncTaskLoader<String>(ctx) {
        override fun loadInBackground(): String? {
            // long time mission
            Thread.sleep(2000)
            return text
        }
    }
}