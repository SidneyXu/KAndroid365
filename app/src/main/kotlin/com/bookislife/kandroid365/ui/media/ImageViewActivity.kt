package com.bookislife.kandroid365.ui.media

import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.view.MotionEvent
import android.widget.ImageView
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R
import com.bookislife.kandroid365.extension.log
import kotlinx.android.synthetic.main.activity_image_view.*
import org.jetbrains.anko.onTouch

/**
 * ImageView Showcase
 *
 * Created by SidneyXu on 2016/05/19.
 */
class ImageViewActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_view)
        setUpToolbar()

        // log
        log("" + resources.getDimension(R.dimen.avatar_small_size))
        log("" + resources.getDimensionPixelSize(R.dimen.avatar_small_size))
        log("" + resources.getDimensionPixelOffset(R.dimen.avatar_small_size))

        // rounded drawable
        val originDrawable = ResourcesCompat.getDrawable(resources, R.drawable.avatar, null) as BitmapDrawable
        val bitmap = originDrawable.bitmap
        val roundedDrawable = RoundedBitmapDrawableFactory.create(resources, bitmap)
        roundedDrawable.cornerRadius = resources.getDimension(R.dimen.avatar_small_size)
        ivRound.setImageDrawable(roundedDrawable)

        // touched drawable
        ivTouched.onTouch { view, motionEvent ->
            val image = view as ImageView
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> image.setColorFilter(Color.argb(100, 255, 255, 255))
                MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> image.clearColorFilter()
            }
            true
        }

        // shader bitmap
        val srcBitmap = (ivMerge.drawable as BitmapDrawable).bitmap
        val borderBitmap = Bitmap.createBitmap(8, 8, Bitmap.Config.RGB_565)
        val canvas = Canvas(borderBitmap)
        canvas.drawARGB(255, 255, 0, 0)
        val targetBitmap = merge(srcBitmap, borderBitmap)
        ivMerge.setImageBitmap(targetBitmap)
    }

    private fun merge(srcBitmap: Bitmap, border: Bitmap): Bitmap {
        val borderSize = 4
        val bitmap = Bitmap.createBitmap(srcBitmap.width + borderSize * 2, srcBitmap.height + borderSize * 2, srcBitmap.config)
        val canvas = Canvas(bitmap)
        val paint = Paint()
        paint.shader = BitmapShader(border, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT)
        canvas.drawPaint(paint);
        canvas.drawBitmap(srcBitmap, 2F, 2F, null)
        return bitmap
    }
}
