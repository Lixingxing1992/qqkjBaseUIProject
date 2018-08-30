package com.base.ui.org.view.sideslip.curvelayout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


/**
 * 测试版
 * 曲线侧滑菜单
 * Created by lixingxing on 2018/7/25.
 */
public class BaseSidesLipCurveLayout extends DrawerLayout implements DrawerLayout.DrawerListener{
    // 菜单区域
    private CurveContentLayout curveContentLayout;
    // 主内容区域
    private View contentView;

    private CurvePutLayout putLayout;

    private float y;
    private float slideOffset;


    public BaseSidesLipCurveLayout(Context context) {
        this(context,null);
    }

    public BaseSidesLipCurveLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BaseSidesLipCurveLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }
    public void init(){
        int childCount = getChildCount();
        if(childCount != 2){
            throw new RuntimeException("CurveContentLayout must has two children");
        }
        // 获取当前侧滑菜单下面的两个控件  一个是内容区  一个是菜单区
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if(view instanceof CurveContentLayout){
                curveContentLayout = (CurveContentLayout) view;
            }else{
                contentView = view;
            }
        }
        if(curveContentLayout == null){
            throw new RuntimeException("CurveContentLayout is null,please create it in your layout-xml");
        }
        removeView(curveContentLayout);

        putLayout = new CurvePutLayout(curveContentLayout);
        addView(putLayout);

        addDrawerListener(this);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        y = ev.getY();

        if(ev.getAction() == MotionEvent.ACTION_UP){
            closeDrawers();
            curveContentLayout.onMotionUP();
            return super.dispatchTouchEvent(ev);
        }

        if(slideOffset < 0.8){
            return super.dispatchTouchEvent(ev);
        }else{
            putLayout.setTouchY(y,slideOffset);
        }

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onDrawerSlide(@NonNull View view, float slideOffset) {
        curveContentLayout.setTouchY(y,slideOffset);
        this.slideOffset  = slideOffset;
        //对内容区域进行偏移
        float translationX = view.getWidth() * slideOffset / 2 ;
        contentView.setTranslationX(translationX);
    }

    @Override
    public void onDrawerOpened(@NonNull View view) {

    }

    @Override
    public void onDrawerClosed(@NonNull View view) {

    }

    @Override
    public void onDrawerStateChanged(int i) {

    }
}
