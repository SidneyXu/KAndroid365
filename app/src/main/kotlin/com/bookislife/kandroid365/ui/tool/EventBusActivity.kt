package com.bookislife.kandroid365.ui.tool

import android.os.Bundle
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R
import com.bookislife.kandroid365.extension.log
import com.bookislife.kandroid365.extension.show
import kotlinx.android.synthetic.main.activity_simple.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.onClick
import kotlin.concurrent.thread

/**
 * EventBus Showcase
 *
 * Created by SidneyXu on 2016/05/25.
 */
class EventBusActivity : BaseActivity() {

    val eventBus = EventBus.getDefault()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple)
        setUpToolbar()

        eventBus.register(this)

        button1.apply {
            show()
            text = getString(R.string.trigger)
            onClick {
                thread {
                    eventBus.post(MessageEvent(getString(R.string.new_message)))
                }
            }
        }
    }

    @Subscribe
    fun onEvent(e: MessageEvent) {
        log("onEvent() run on ${Thread.currentThread().name}")
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(e: MessageEvent) {
        log("onEventMainThread() run on ${Thread.currentThread().name}")
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    fun onEventBackground(e: MessageEvent) {
        log("onEventBackground() run on ${Thread.currentThread().name}")
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    fun onEventAsync(e: MessageEvent) {
        log("onEventAsync() run on ${Thread.currentThread().name}")
    }

    override fun onDestroy() {
        eventBus.unregister(this)
        super.onDestroy()
    }

}
