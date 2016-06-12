package com.bookislife.koin

import com.bookislife.koin.extension.StyledText
import com.bookislife.koin.extension.preferences
import com.bookislife.koin.extension.styledText
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

/**
 * Created by SidneyXu on 2016/05/20.
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = intArrayOf(21), manifest = Config.NONE)
class KotlinTest{

    @Test
    fun test01(){
        val s="saa"
        RuntimeEnvironment.application.applicationContext.styledText {

        }

        RuntimeEnvironment.application.applicationContext.preferences {
        }
    }
}