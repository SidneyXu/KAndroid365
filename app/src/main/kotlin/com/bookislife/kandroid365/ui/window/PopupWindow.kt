package com.bookislife.kandroid365.ui.window

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bookislife.kandroid365.BaseActivity

/**
 * Created by SidneyXu on 2016/05/30.
 */
class PopupWindow:BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun createMenu(){
        val inflater = LayoutInflater.from(this@PopupWindow)
//        val view = inflater.inflate(R.layout.setting_action_view, null)
//        val mWindow = PopupWindow(view)
//        val h = this@MainActivity.getWindowManager().getDefaultDisplay().getHeight()
//        val w = this@MainActivity.getWindowManager().getDefaultDisplay().getWidth()
//        mWindow.setWidth(w / 2 + 50)
//        mWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
//        mWindow.setFocusable(true)
//        mWindow.setAnimationStyle(R.style.mystyle)
//        val dw = ColorDrawable(0)
//        mWindow.setBackgroundDrawable(dw)
//        mWindow.showAsDropDown(this@MainActivity.findViewById(R.id.action_overflow))
    }
}