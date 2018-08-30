package com.base.ui.org.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2018/6/7.
 */

public class MyAutoRelativeLayout extends RelativeLayout {
    public MyAutoRelativeLayout(Context context) {
        super(context);
    }

    public MyAutoRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyAutoRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    static boolean isFlag = true;

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {


        if(isFlag){
            float scaleX =  UIUtils.getInstance(this.getContext()).getHorizontalScaleValue();
            float scaleY =  UIUtils.getInstance(this.getContext()).getVerticalScaleValue();
            measureView(this,scaleX,scaleY);
            isFlag = false;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void measureView(View child,float scaleX,float scaleY){
        if(child instanceof ViewGroup){

            LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
            layoutParams.width = (int) (layoutParams.width * scaleX);
            layoutParams.height = (int) (layoutParams.height * scaleY);
            layoutParams.rightMargin = (int) (layoutParams.rightMargin * scaleX);
            layoutParams.leftMargin = (int) (layoutParams.leftMargin * scaleX);
            layoutParams.topMargin = (int) (layoutParams.topMargin * scaleY);
            layoutParams.bottomMargin = (int) (layoutParams.bottomMargin * scaleY);

            int count = ((ViewGroup)child).getChildCount();
            for (int i = 0;i < count;i++){
                View childs = this.getChildAt(i);
                measureView(childs,scaleX,scaleY);
            }
        }else{
            //代表的是当前空间的所有属性列表
            LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
            layoutParams.width = (int) (layoutParams.width * scaleX);
            layoutParams.height = (int) (layoutParams.height * scaleY);
            layoutParams.rightMargin = (int) (layoutParams.rightMargin * scaleX);
            layoutParams.leftMargin = (int) (layoutParams.leftMargin * scaleX);
            layoutParams.topMargin = (int) (layoutParams.topMargin * scaleY);
            layoutParams.bottomMargin = (int) (layoutParams.bottomMargin * scaleY);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
