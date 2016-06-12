package com.bookislife.kandroid365.ui.net

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R
import com.bookislife.kandroid365.extension.show
import kotlinx.android.synthetic.main.activity_simple.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.toast
import java.io.File

/**
 * Download Manager Showcase
 *
 * Created by SidneyXu on 2016/05/17.
 */
class DownloadActivity : BaseActivity() {

    var downloadId = -1L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple)
        setUpToolbar()

        val downloadManager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

        button1.show()
        button1.text = getString(R.string.download)
        button1.onClick {
            val uri = Uri.parse("http://www.github.com/index.html")
            val request = DownloadManager.Request(uri)
            request.setTitle("Download Sample")
            request.setDescription("Download Sample...")

            // suffix -1,-2.. would be appended if file exists
            request.setDestinationUri(Uri.fromFile(File(Environment.getExternalStorageDirectory(), "foobar.html")))
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            downloadId = downloadManager.enqueue(request)
        }

        val downloadFilter = IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
        registerReceiver(downloadReceiver, downloadFilter)
    }

    val downloadReceiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent) {
            val queryId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
            if (queryId != -1L && downloadId == queryId) {
                toast("Finish download")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(downloadReceiver)
    }
}