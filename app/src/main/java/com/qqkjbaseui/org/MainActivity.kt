package com.qqkjbaseui.org

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View

import com.qqkjbaseui.org.animation.AnimationActivity
import com.qqkjbaseui.org.auto.TestAutoActivity
import com.qqkjbaseui.org.corrdinatorlayout.CoordinatorLayoutActivity
import com.qqkjbaseui.org.edittext.EditTextActivity
import com.qqkjbaseui.org.recyleview.TestRecyleTouchActivity
import com.qqkjbaseui.org.sideslip.SidesLipMenuActivity
import com.zhy.autolayout.AutoLayoutActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AutoLayoutActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun onBtnClick(view: View) {
        when (view.id) {
            // 侧滑菜单
            R.id.sideslip -> startActivity(Intent(this, SidesLipMenuActivity::class.java))
            // 测试 EditText
            R.id.editText -> startActivity(Intent(this, EditTextActivity::class.java))
            R.id.coordinatorLayout -> startActivity(Intent(this,CoordinatorLayoutActivity::class.java))
            R.id.autoLayout -> startActivity(Intent(this, TestAutoActivity::class.java))
            R.id.recyclerview -> startActivity(Intent(this, TestRecyleTouchActivity::class.java))
            R.id.animation -> startActivity(Intent(this, AnimationActivity::class.java))
            R.id.progress -> Log.e("lixingxingOnTouch", "onBtnClick1")
            R.id.dialog -> Log.e("lixingxingOnTouch", "onBtnClick2")
            R.id.http -> Log.e("lixingxingOnTouch", "onBtnClick3")
            R.id.activity -> Log.e("lixingxingOnTouch", "onBtnClick4")
            else -> {
            }
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN)
            Log.e("lixingxingOnTouch", " activity dispatchTouchEvent")
        return super.dispatchTouchEvent(ev)
    }


    override fun onUserInteraction() {
        super.onUserInteraction()

    }
}
