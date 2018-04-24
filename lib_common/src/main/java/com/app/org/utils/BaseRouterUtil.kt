package com.app.org.utils

import android.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter

/**
 * Created by lixingxing on 2018/4/23.
 */


fun startActivity(activityName:String) {
    ARouter.getInstance().build(activityName).navigation()
}


fun createFragment(fragmentName:String) :Fragment{
    return ARouter.getInstance().build(fragmentName).navigation() as Fragment
}