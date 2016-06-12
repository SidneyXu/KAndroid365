package com.bookislife.kandroid365.ui.media

import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Bundle
import android.os.Environment
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R
import kotlinx.android.synthetic.main.activity_audio.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.toast
import java.io.IOException

/**
 * Audio Showcase
 *
 * Created by SidneyXu on 2016/05/18.
 */
class AudioActivity : BaseActivity() {

    var recorder: MediaRecorder? = null
    var player: MediaPlayer? = null
    var fileName = ""
    var isRecording = true
    var isPlaying = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio)
        setUpToolbar()

        val parentDir = Environment.getExternalStorageDirectory().absolutePath
        fileName = parentDir + "/foobar.3gp"
        text1.text = fileName

        btnRecord.text = getString(R.string.start_record)
        btnRecord.onClick {
            onRecord(isRecording)
            btnRecord.text = if (isRecording) {
                getString(R.string.stop_record)
            } else {
                getString(R.string.start_record)
            }
            isRecording = !isRecording
        }

        btnPlay.text = getString(R.string.start_play)
        btnPlay.onClick {
            onPlay(isPlaying)
            btnPlay.text = if (isPlaying) {
                getString(R.string.stop_play)
            } else {
                getString(R.string.start_play)
            }
            isPlaying = !isPlaying
        }
    }

    override fun onPause() {
        super.onPause()
        recorder?.release()
        recorder = null

        player?.release()
        player = null
    }

    private fun onRecord(start: Boolean) {
        if (start) {
            startRecording()
        } else {
            stopRecording()
        }
    }

    private fun stopRecording() {
        recorder?.stop()
        recorder?.release()
        recorder = null
    }

    private fun startRecording() {
        try {
            recorder = MediaRecorder().apply {
                setAudioSource(MediaRecorder.AudioSource.MIC)
                setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
                setOutputFile(fileName)
                setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
                prepare()
                start()
            }
        } catch(e: IOException) {
            toast("prepare failed")
        }
    }

    private fun onPlay(start: Boolean) {
        if (start) {
            startPlaying()
        } else {
            stopPlaying()
        }
    }

    private fun stopPlaying() {
        player?.release()
        player = null
    }

    private fun startPlaying() {
        try {
            player = MediaPlayer().apply {
                setDataSource(fileName)
                setOnCompletionListener {
                    btnPlay.performClick()
                }
                prepare()
                start()
            }
        } catch(e: IOException) {
            toast("prepare failed")
        }
    }
}