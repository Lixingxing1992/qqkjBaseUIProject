package com.qqkjbaseui.org.splash;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;

import com.base.ui.org.animation.SplashView;
import com.qqkjbaseui.org.MainActivity;

/**
 * 属性动画，API3.0之后提出的动画模式
 * 优点：
 * 1.不在局限于View 对象，无对象也可以进行动画处理
 * 2.不再局限于4种基本变换：平移、旋转、缩放 、透明度
 * 3.可以灵活的操作任意对象属性，根据自己业务来实现自己想要的结果
 * 核心：
 * 1.ObjectAnimator  对象动画（重点）
 * 2.ValueAnimator	  值动画(重点)
 * 3.PropertyValueHolder 用于多个动画同时执行
 * 4.TypeEvaluator 估值器
 * 6.AnimatorSet 动画集合
 * 5.Interpolator 插值器
 */
public class SplashActivity extends AppCompatActivity {

    private View iv;
    private FrameLayout mMainView;
    private SplashView splashView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.activity_main);
//        iv = findViewById(R.id.img_iv);



        //将动画层盖在实际的操作图层上
        mMainView = new FrameLayout(this);
        ContentView contentView = new ContentView(this);
        mMainView.addView(contentView);
        splashView = new SplashView(this);
        mMainView.addView(splashView);

        setContentView(mMainView);

        //后台开始加载数据
        startLoadData();

        splashView.setOnfinishCallBack(new SplashView.OnfinishCallBack() {
            @Override
            public void onFinish() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        });



    }
    //    ------------------------加载动画--------------------------------------
    Handler handler = new Handler();
    private void startLoadData() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //数据加载完毕，进入主界面--->开启后面的两个动画
                splashView.splashDisappear();
            }
        },2000);//延迟时间
    }



}
