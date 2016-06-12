package com.bookislife.kandroid365.ui.system

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by SidneyXu on 2016/06/08.
 */
class Duration {
    private var start: Date = Date()

    companion object {

        fun formatDuration(seconds: Int): String {
            val seconds = seconds % 60
            val minutes = (seconds - seconds) / 60 % 60
            val hours = (seconds - minutes * 60 - seconds) / 3600

            return "$hours:$minutes:$seconds"
        }
    }

    fun getDurationSeconds(end: Long): Int {
        return ((end - start.time) / 1000).toInt()
    }
}