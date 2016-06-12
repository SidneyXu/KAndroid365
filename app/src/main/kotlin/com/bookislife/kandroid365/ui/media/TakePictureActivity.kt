package com.bookislife.kandroid365.ui.media

import android.app.Activity
import android.content.Context
import android.hardware.Camera
import android.os.Build
import android.os.Bundle
import android.view.SurfaceHolder
import android.view.Window
import android.view.WindowManager
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R
import com.bookislife.kandroid365.extension.log
import com.bookislife.kandroid365.extension.setResultBundle
import kotlinx.android.synthetic.main.activity_take_picture.*
import org.jetbrains.anko.onClick
import java.io.IOException
import java.util.*

/**
 * Created by SidneyXu on 2016/05/17.
 */
class TakePictureActivity : BaseActivity() {

    companion object {
        val EXTRA_FILE_NAME = "filename"
    }

    private var holder: SurfaceHolder? = null
    private var camera: Camera? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // fullscreen
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        setContentView(R.layout.activity_take_picture)

        surface.onClick {
            camera?.autoFocus { success, camera ->

            }
        }
        holder = surface.holder
        // deprecated, but required for pre-3.0 devices
        surface.holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS)
        surface.holder.addCallback(holderCallback)

        btnTakePicture.onClick {
            camera?.let {
                it.takePicture(Camera.ShutterCallback {
                    log("onShutter()")
                }, null, Camera.PictureCallback { bytes, camera ->
                    log("onPictureTaken()")

                    val filename = UUID.randomUUID().toString() + ".jpg"
                    openFileOutput(filename, Context.MODE_PRIVATE).use {
                        it.write(bytes)
                    }
                    setResultBundle(Activity.RESULT_OK, intent) {
                        putString(EXTRA_FILE_NAME, filename)
                    }
                })
            }
        }
    }

    val holderCallback = object : SurfaceHolder.Callback {
        override fun surfaceChanged(holder: SurfaceHolder, f: Int, w: Int, h: Int) {
            camera?.let {
                val previewSizes = it.parameters.supportedPreviewSizes
                val bestSize = getBestSupportedSize(previewSizes)
                it.parameters.setPreviewSize(bestSize.width, bestSize.height)
                it.parameters.setPictureSize(bestSize.width, bestSize.height)
                try {
                    it.startPreview()
                } catch(e: Exception) {
                    releaseCamera()
                }
            }
        }

        override fun surfaceDestroyed(holder: SurfaceHolder) {
            camera?.stopPreview()
        }

        override fun surfaceCreated(holder: SurfaceHolder) {
            try {
                camera?.setPreviewDisplay(holder)
            } catch(e: IOException) {
                e.printStackTrace()
            }
        }

    }

    private fun getBestSupportedSize(supportedSize: List<Camera.Size>): Camera.Size {
        var bestSize = supportedSize[0]
        var largestArea = bestSize.width * bestSize.height
        supportedSize.forEach {
            val area = it.width * it.height
            if (area > largestArea) {
                bestSize = it
                largestArea = area
            }
        }
        return bestSize;
    }

    private fun releaseCamera() {
        if (camera != null) {
            camera?.release()
            camera = null
        }
    }

    private fun openCamera() {
        releaseCamera()
        //Fail to connect to camera service
        camera = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            Camera.open(0)
        } else {
            Camera.open()
        }
        camera?.setDisplayOrientation(90)
    }

    override fun onResume() {
        super.onResume()
        openCamera()
    }

    override fun onPause() {
        super.onPause()
        releaseCamera()
    }
}