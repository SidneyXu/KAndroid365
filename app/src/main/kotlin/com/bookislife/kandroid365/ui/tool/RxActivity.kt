package com.bookislife.kandroid365.ui.tool

import android.os.Bundle
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R
import com.bookislife.kandroid365.extension.log
import com.bookislife.kandroid365.extension.show
import kotlinx.android.synthetic.main.activity_simple.*
import org.jetbrains.anko.onClick
import rx.Observable
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

/**
 * RxJava Showcase
 *
 * Created by SidneyXu on 2016/05/31.
 */
class RxActivity : BaseActivity() {

    val pendingSubscriptions = CompositeSubscription()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple)
        setUpToolbar()

        button1.show()
        button1.onClick {
            Observable.from(arrayOf(10,20,30))
                    .observeOn(Schedulers.io())
                    .subscribe({
                        Thread.sleep(6000)
                        log("$it observeOn ${Thread.currentThread().name}")
                    }, {
                        it.printStackTrace()
                    })
        }

        button2.show()
        button2.onClick {
            Observable.from(arrayOf(50,60,70))
                    .observeOn(Schedulers.newThread())
                    .subscribe({
                        Thread.sleep(6000)
                        log("$it observeOn ${Thread.currentThread().name}")
                    }, {
                        it.printStackTrace()
                    })
        }

        button3.show()
        button3.onClick {
            val subscription = Observable.from(arrayOf(100,200,300))
                    .observeOn(Schedulers.io())
                    .subscribe {
                        Thread.sleep(6000)
                        log("$it observeOn ${Thread.currentThread().name}")
                    }
            pendingSubscriptions.add(subscription)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // unsubscribe all
        pendingSubscriptions.clear()
    }
}