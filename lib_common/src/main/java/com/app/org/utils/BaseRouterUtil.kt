package com.app.org.utils

import android.support.v4.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter

/**
 * 路由启动工具类
 * Created by lixingxing on 2018/4/23.
 */


fun startActivity(activityName:String) {
    ARouter.getInstance().build(activityName).navigation()
}


fun createFragment(fragmentName:String) : Fragment {
    return ARouter.getInstance().build(fragmentName).navigation() as Fragment
}