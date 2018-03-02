package com.tangtown.org.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.github.anzewei.parallaxbacklayout.ParallaxBack;

import com.app.org.utils.ToastUtils;

/**
 * Created by lixingxing on 2018/1/5.
 */
/*      其中edge控制滑动方向，layout设置滑动效果**

        ## 滑动效果默认有3种方式

        - PARALLAX 视差返回，类似微信的滑动返回效果
        - COVER 上级activity不会滑动，只滑动当前activity，有点像抽屉
        - SLIDE 跟随滑动，上一级会紧贴着当前activity滑动*/

@ParallaxBack(edge = ParallaxBack.Edge.LEFT,layout = ParallaxBack.Layout.PARALLAX)
public class TestActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void finish() {
        ToastUtils.showShortToast("finish");
        super.finish();
    }
}
