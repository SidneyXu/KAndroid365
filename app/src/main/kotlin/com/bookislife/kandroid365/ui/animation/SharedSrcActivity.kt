package com.bookislife.kandroid365.ui.animation

import android.annotation.TargetApi
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.transition.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import android.widget.TextView
import com.bookislife.kandroid365.BaseActivity
import com.bookislife.kandroid365.R
import com.bookislife.kandroid365.extension.makeSceneTransition
import com.bookislife.kandroid365.extension.navigateTo
import com.bookislife.kandroid365.model.local.Labs
import kotlinx.android.synthetic.main.activity_recycler.*
import org.jetbrains.anko.find
import org.jetbrains.anko.onClick
import org.jetbrains.anko.textColor
import java.util.*

/**
 * Created by Sidney on 2016/8/17.
 */
class SharedSrcActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)
        setUpToolbar()

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.setHasFixedSize(true)
        recycler.adapter = Apt(Labs.fruits)

        btnShow.onClick {
            if (Build.VERSION.SDK_INT >= 21) {
                TransitionManager.beginDelayedTransition(recycler, Slide())
            }
            recycler.visibility = View.VISIBLE
        }

        // speed up shared transition
        if (Build.VERSION.SDK_INT >= 21) {
            window.sharedElementEnterTransition = enterTransition()
            window.sharedElementReturnTransition = returnTransition()
        }
    }

    @TargetApi(19)
    fun enterTransition(): Transition {
        val bounds = ChangeBounds()
        bounds.duration = 2000
        return bounds
    }

    @TargetApi(19)
    fun returnTransition(): Transition {
        val bounds = ChangeBounds()
        bounds.interpolator = DecelerateInterpolator()
        bounds.duration = 2000
        return bounds
    }

    inner class Apt(val items: List<String>) : RecyclerView.Adapter<Apt.VH>() {
        override fun onBindViewHolder(holder: VH?, position: Int) {
            holder?.apply {
                text1.text = items[position]
                val random = Random()
                text1.textColor = Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255))
                image1.setImageResource(R.drawable.avatar)
            }
        }

        override fun getItemCount(): Int = items.size

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): VH {
            val view = LayoutInflater.from(this@SharedSrcActivity).inflate(R.layout.item_card, parent, false)
            return VH(view)
        }

        inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val text1: TextView
            val image1: ImageView

            init {
                text1 = itemView.find<TextView>(R.id.text1)
                image1 = itemView.find<ImageView>(R.id.image1)
                itemView.setOnClickListener {
                    ViewCompat.setTransitionName(itemView, getString(R.string.transition_foobar))
                    makeSceneTransition(SharedTargetActivity::class.java, itemView, getString(R.string.transition_foobar))
                }
            }
        }
    }
}