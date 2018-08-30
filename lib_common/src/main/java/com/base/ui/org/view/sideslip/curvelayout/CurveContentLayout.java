package com.base.ui.org.view.sideslip.curvelayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.base.ui.org.R;

/**
 * 曲线侧滑菜单内容区域
 * Created by lixingxing on 2018/7/25.
 */
public class CurveContentLayout extends LinearLayout {

    float maxTranslationX = 10;

    public CurveContentLayout(Context context) {
        this(context,null);
    }

    public CurveContentLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CurveContentLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void init(Context context, @Nullable AttributeSet attrs) {
        setOrientation(VERTICAL);

        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.SideslipLayout);
            maxTranslationX = a.getDimension(R.styleable.SideslipLayout_maxTranslationX, 0);
            a.recycle();
        }
    }

    boolean opened = false;

    /**
     * 控制控件摆放位置
     * @param touchY  控制点y坐标
     * @param percent 百分比
     */
    public void setTouchY(float touchY, float percent) {

        opened = percent > 0.8;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);

            child.setPressed(false);

            //判断
            boolean isHover = opened && touchY > child.getTop() && touchY < child.getBottom();
            if (isHover) {
                child.setPressed(true);
            }

            apply(child, touchY);
        }


    }


    public void apply(View child, float y) {
        //最终控件x偏移量
        float translationX = 0;

        //计算控件中心点
        float centerY = child.getTop() + child.getHeight() / 2;

        //首先得到手指距离差
        float distance = Math.abs(y - centerY);

        //放大三倍系数
        float scale = distance / getHeight() * 3;

        translationX = maxTranslationX - scale * maxTranslationX;

        child.setTranslationX(translationX);
    }

    public void onMotionUP() {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child.isPressed()) {
                child.performClick();
            }
        }
    }
}
