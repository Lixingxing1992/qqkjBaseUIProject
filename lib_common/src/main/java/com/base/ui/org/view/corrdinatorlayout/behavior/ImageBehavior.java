package com.base.ui.org.view.corrdinatorlayout.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.base.ui.org.view.corrdinatorlayout.Behavior;

/**
 * Created by lixingxing on 2018/7/23.
 */
public class ImageBehavior extends Behavior {

    private int maxHeight = 400;
    private int originHeight;

    public ImageBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public void onLayoutFinish(View parent, View child) {
        if (originHeight == 0) {
            originHeight = child.getHeight();
        }
    }

    //    当子view调用dispatchNestedPreScroll方法时,会调用该方法.
    //    该方法的会传入内部View移动的dx,dy，如果你需要消耗一定的dx,dy，就通过最后一个参数
    //    consumed进行指定，例如我要消耗一半的dy，就可以写consumed[1]=dy/2
    // 参数target:同上
    // 参数dxConsumed:表示target已经消费的x方向的距离
    // 参数dyConsumed:表示target已经消费的y方向的距离
    // 参数dxUnconsumed:表示x方向剩下的滑动距离
    // 参数dyUnconsumed:表示y方向剩下的滑动距离
    @Override
    public void onNestedScroll(View scrollView, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {

        if (scrollView.getScrollY() > 0) {
            ViewGroup.LayoutParams layoutParams = target.getLayoutParams();
            layoutParams.height = layoutParams.height - Math.abs(dyConsumed);
            if (layoutParams.height < originHeight) {
                layoutParams.height = originHeight;
            }
            target.setLayoutParams(layoutParams);
        } else if (scrollView.getScrollY() == 0) {
            ViewGroup.LayoutParams layoutParams = target.getLayoutParams();
            //为0
            layoutParams.height = layoutParams.height + Math.abs(dyUnconsumed);
            if (layoutParams.height >= maxHeight) {
                layoutParams.height = maxHeight;
            }
            target.setLayoutParams(layoutParams);
        }

    }
}
