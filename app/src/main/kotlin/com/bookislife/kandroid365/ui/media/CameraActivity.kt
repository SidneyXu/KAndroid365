package com.bookislife.kandroid365.ui.media

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Point
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R
import com.bookislife.kandroid365.extension.log
import com.bookislife.kandroid365.extension.navigateForResult
import kotlinx.android.synthetic.main.activity_camera.*
import org.jetbrains.anko.onClick

/**
 * Camera Showcase
 *
 * Created by SidneyXu on 2016/06/05.
 */
class CameraActivity : BaseActivity() {

    companion object {
        val REQUEST_PHOTO = 10000
    }

    var path: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        setUpToolbar()

        btnCapture.onClick {
            navigateForResult(TakePictureActivity::class.java, REQUEST_PHOTO)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != Activity.RESULT_OK || data == null) {
            return
        }
        when (requestCode) {
            REQUEST_PHOTO -> {
                val filename = data.getStringExtra(TakePictureActivity.EXTRA_FILE_NAME)
                path = getFileStreamPath(filename).absolutePath

                log("path is $path")

                showImage()
            }
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun showImage() {
        path?.let {
            image1.setImageBitmap(getScaledDrawable(it))
        }
    }

    private fun getScaledDrawable(path: String): Bitmap {
        val display = windowManager.defaultDisplay
        val destPoint = Point()
        display.getSize(destPoint)
        val destHeight = destPoint.y
        val destWidth = destPoint.x

        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeFile(path, options)

        val srcWidth = options.outWidth
        val srcHeight = options.outHeight

        var inSampleSize = 1L
        if (srcHeight > destHeight || srcWidth > destWidth) {
            if (srcWidth > srcHeight) {
                inSampleSize = Math.ceil(srcHeight.toDouble() / destHeight).toLong()
            } else {
                inSampleSize = Math.ceil(srcWidth.toDouble() / destWidth).toLong()
            }
        }

        val options2 = BitmapFactory.Options()
        options2.inSampleSize = inSampleSize.toInt()

        log("sample size is $inSampleSize, window size is $destWidth*$destHeight")

        return BitmapFactory.decodeFile(path, options2)
    }

    override fun onStart() {
        super.onStart()
        showImage()
    }

    override fun onStop() {
        super.onStop()
        clearBitmap()
    }

    private fun clearBitmap() {
        val drawable = image1.drawable
        if (drawable is BitmapDrawable) {
            drawable.bitmap.recycle()
            image1.setImageDrawable(null)
        }
    }
}