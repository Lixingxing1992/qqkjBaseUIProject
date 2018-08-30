package com.qqkjbaseui.org.edittext

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.base.ui.org.view.BaseToast
import com.base.ui.org.view.edittext.click.BaseDrawablePosition
import com.base.ui.org.view.edittext.click.OnDrawableClickListener

import com.qqkjbaseui.org.R
import kotlinx.android.synthetic.main.activity_edittext.*

/**
 * Created by lixingxing on 2018/7/24.
 */
class EditTextActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edittext)
        drawableEditTextLeft.setDrawableClickListener(object : OnDrawableClickListener {
            override fun onClick(target: BaseDrawablePosition) {
                when (target) {
                    BaseDrawablePosition.RIGHT -> {}
                        BaseDrawablePosition.LEFT -> {BaseToast.showToast("点击了左边")}
                    BaseDrawablePosition.TOP -> {}
                    BaseDrawablePosition.BOTTOM -> {}
                }
            }
        })
    }
}
