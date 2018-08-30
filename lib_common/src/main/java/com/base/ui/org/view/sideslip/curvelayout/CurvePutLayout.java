package com.base.ui.org.view.sideslip.curvelayout;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * 曲线侧滑菜单组合类
 * Created by lixingxing on 2018/7/25.
 */
public class CurvePutLayout extends RelativeLayout{

    // 背景
    CurveBgView bgView;
    // 菜单内容
    CurveContentLayout contentLayout;

    public CurvePutLayout(CurveContentLayout contentLayout){
        super(contentLayout.getContext());
        this.contentLayout = contentLayout;
        init(contentLayout);
    }

    public CurvePutLayout(Context context) {
        super(context);
    }

    public CurvePutLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CurvePutLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void init(CurveContentLayout contentLayout){
        // 转移菜单内容的宽高给当前
        setLayoutParams(contentLayout.getLayoutParams());

        // 把背景添加进来
        bgView = new CurveBgView(getContext());
        bgView.setColor(contentLayout.getBackground());
        contentLayout.setBackgroundColor(Color.TRANSPARENT);

        // 组合进行设置
        addView(bgView);
        addView(contentLayout,new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));

    }


    /**
     *  控住控件摆放位置
     * @param touchY    控制点y坐标
     * @param percent   百分比
     */
    public void setTouchY(float touchY,float percent){
        if(bgView != null){
            bgView.setTouchY(touchY,percent);
        }
        if(contentLayout != null){
            contentLayout.setTouchY(touchY,percent);
        }
    }

}
