package com.bookislife.kandroid365.ui.storage

import android.os.Bundle
import android.os.Environment
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R
import kotlinx.android.synthetic.main.activity_file.*
import java.io.File

/**
 * File Showcase
 *
 * Created by SidneyXu on 2016/05/19.
 */
class FileActivity : BaseActivity() {

    /**
     * Reference: http://developer.android.com/intl/ja/google/play/expansion-files.html
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file)
        setUpToolbar()

        initTemplateFile()
        initExternalFile()
        initCacheFile()
        initObbFile()
        initStatus()
    }

    private fun initTemplateFile() {
        val content = buildString {
            val file = File.createTempFile("myTempFile", ".suffix")
            file.createNewFile()

            append("""File.createTempFile("myTempFile", ".suffix")""")
            append("\n--->\n")
            append("${file.absolutePath}\n\n")

            val file2 = File.createTempFile("myTempFile", ".suffix", cacheDir)
            file2.createNewFile()

            append("""File.createTempFile("myTempFile", ".suffix", cacheDir)""")
            append("\n--->\n")
            append(file2.absolutePath)
        }
        text1.text = content
    }

    private fun initExternalFile() {
        val content = buildString {
            // public external storage, wouldn't be removed when uninstall
            val file = File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES), "myPublicPicture")
            file.createNewFile()

            append("""File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES), "myPublicPicture")""")
            append("\n--->\n")
            append("${file.absolutePath}\n\n")

            // private external storage, would be removed when uninstall
            val file2 = File(getExternalFilesDir(
                    Environment.DIRECTORY_PICTURES), "myPrivatePicture")
            file2.createNewFile()

            append("""File(getExternalFilesDir(
                    Environment.DIRECTORY_PICTURES), "myPrivatePicture")""")
            append("\n--->\n")
            append(file2.absolutePath)
        }
        text2.text = content
    }

    private fun initCacheFile() {
        val content = buildString {
            val file = File(externalCacheDir, "myPrivateCache")
            file.createNewFile()

            append("""File(externalCacheDir, "myPrivateCache")""")
            append("\n--->\n")
            append("${file.absolutePath}\n\n")

            val file2 = File(cacheDir, "myCache")
            file2.createNewFile()

            append("""File(cacheDir, "myCache")""")
            append("\n--->\n")
            append(file2.absolutePath)
        }
        text3.text = content
    }

    private fun initObbFile() {
        val content = buildString {
            val path = "${Environment.getExternalStorageDirectory().absolutePath}/Android/obb/$packageName/main.obb"
            val file = File(path)
            if (!file.parentFile.exists()) {
                file.parentFile.mkdirs()
            }
            file.createNewFile()

            append("""Obb File""")
            append("\n--->\n")
            append("${file.absolutePath}")
        }
        text4.text = content
    }

    private fun initStatus() {
        val content = buildString {
            append("isExternalStorageReadable() ---> ${isExternalStorageReadable()}")
            append("\n")
            append("isExternalStorageWritable() ---> ${isExternalStorageWritable()}")
        }
        text5.text = content
    }

    /* Checks if external storage is available for read and write */
    fun isExternalStorageWritable(): Boolean {
        val state = Environment.getExternalStorageState()
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true
        }
        return false
    }

    /* Checks if external storage is available to at least read */
    fun isExternalStorageReadable(): Boolean {
        val state = Environment.getExternalStorageState()
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true
        }
        return false
    }

}