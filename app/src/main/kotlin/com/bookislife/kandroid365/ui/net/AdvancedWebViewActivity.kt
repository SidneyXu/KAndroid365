package com.bookislife.kandroid365.ui.net

import android.content.Context
import android.net.http.SslError
import android.os.Bundle
import android.util.AttributeSet
import android.webkit.SslErrorHandler
import android.webkit.WebView
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.widget.ProgressWebView
import org.jetbrains.anko.alert

/**
 * More WebView Showcase
 *
 * Created by SidneyXu on 2016/05/16.
 */
class AdvancedWebViewActivity : BaseActivity() {

    val webview  by lazy {
        AdvancedWebView(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(webview)

        webview.setDebugged(true)
        webview.disableSideEffect()
        webview.loadUrl("http://www.github.com")
    }

    override fun onBackPressed() {
        if (webview.canGoBack()) {
            webview.goBack()
            return
        }
        super.onBackPressed()
    }

    inner class AdvancedWebView : ProgressWebView {

        constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr)

        override val webViewClient: InternalWebViewClient
            get() = object : InternalWebViewClient() {
                override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler, error: SslError?) {
                    alert {
                        message("ssl certificate verify failed, continuing reading?")
                        positiveButton {
                            handler.proceed()
                        }
                        negativeButton {
                            handler.cancel()
                        }
                    }.show()
                }
            }
    }
}
