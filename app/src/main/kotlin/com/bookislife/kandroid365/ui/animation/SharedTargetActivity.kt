package com.bookislife.kandroid365.ui.animation

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.view.View
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R
import kotlinx.android.synthetic.main.activity_target.*

/**
 * Created by Sidney on 2016/8/18.
 */
class SharedTargetActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_target)
        setUpToolbar()
        ViewCompat.setTransitionName(image1, getString(R.string.transition_foobar))
        postponeEnterTransition()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        scheduleStartPostponedTransition(image1)
    }

    fun scheduleStartPostponedTransition(sharedElement: View) {
        sharedElement.viewTreeObserver.addOnPreDrawListener {
//                sharedElement.viewTreeObserver.removeOnPreDrawListener()
            startPostponedEnterTransition()
            true
        }
    }

    override fun onActivityReenter(resultCode: Int, data: Intent?) {
        super.onActivityReenter(resultCode, data)

        // Postpone the shared element return transition.
        postponeEnterTransition()
    }
}