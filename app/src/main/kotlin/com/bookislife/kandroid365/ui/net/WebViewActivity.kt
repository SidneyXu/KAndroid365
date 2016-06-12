package com.bookislife.kandroid365.ui.net

import android.os.Build
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R
import kotlinx.android.synthetic.main.activity_webview.*

/**
 *  WebView Showcase
 *
 * Created by SidneyXu on 2016/04/03.
 */
class WebViewActivity : BaseActivity() {

    /**
     * Debug on Chrome
     *
     * chrome://inspect/#devices
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        setUpToolbar()

        webview.settings.javaScriptEnabled = true
        webview.setWebViewClient(WebViewClient())
        webview.setWebChromeClient(WebChromeClient())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true)
        }
        webview.loadUrl("https://www.github.com")
    }
}