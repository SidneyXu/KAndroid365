package com.bookislife.kandroid365.widget

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

/**
 * Created by SidneyXu on 2016/05/17.
 */
open class ProgressWebView : WebView {

    private var debugged: Boolean = false
    private var progressDialog: ProgressDialog? = null

    @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr) {
        initialize()
    }

    fun setDebugged(enabled: Boolean) {
        debugged = enabled
        if (debugged && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true)
        }
    }

    fun disableSideEffect() {
        overScrollMode = View.OVER_SCROLL_NEVER
    }

    private fun initialize() {
        val webSettings = settings
        webSettings.javaScriptEnabled = true
        isClickable = true
        isFocusableInTouchMode = true
        setWebViewClient(webViewClient)
    }

    open protected val webViewClient: InternalWebViewClient
        get() = InternalWebViewClient()

    open inner class InternalWebViewClient : WebViewClient() {

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            if (null != progressDialog) {
                progressDialog!!.dismiss()
            }
        }

        override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            if (null == progressDialog) {
                progressDialog = ProgressDialog(context)
                progressDialog!!.setProgressStyle(ProgressDialog.STYLE_SPINNER)
            }
            progressDialog!!.show()
        }

        override fun onReceivedError(view: WebView?, errorCode: Int, description: String?, failingUrl: String?) {
            super.onReceivedError(view, errorCode, description, failingUrl)
            if (null != progressDialog) {
                progressDialog!!.dismiss()
            }
        }
    }
}