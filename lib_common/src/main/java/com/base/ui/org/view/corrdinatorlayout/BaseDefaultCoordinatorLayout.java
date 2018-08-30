package com.base.ui.org.view.corrdinatorlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.widget.NestedScrollView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;

import com.base.ui.org.R;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 测试版
 * 仿照 coordinatorLayout原理 实现 自定义 coordinatorLayout
 * --》 下拉出现图片 toolBar隐藏，上滑图片隐藏 toolBar显示
 * Created by lixingxing on 2018/7/23.
 */
public class BaseDefaultCoordinatorLayout extends RelativeLayout implements NestedScrollingParent,ViewTreeObserver.OnGlobalLayoutListener {
    public BaseDefaultCoordinatorLayout(Context context) {
        super(context);

    }


    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        for (int i = 0;i < getChildCount();i++){
            View v = getChildAt(i);
            LayoutParams lp = (LayoutParams) v.getLayoutParams();
            if(lp.behavior != null){
                lp.behavior.onNestedScroll(target,v,dxConsumed,dyConsumed,dxUnconsumed,dyUnconsumed );
            }
        }
    }


    float lastX ;
    float lastY ;

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                lastX = event.getX();
//                lastY = event.getY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//
//                break;
//        }
//    }

    public BaseDefaultCoordinatorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseDefaultCoordinatorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    /**
     * 当前实现的NestedScrolling机制，在进行滚动的时候，这里必须是为true
     * @param child
     * @param target
     * @param nestedScrollAxes
     * @return
     */
    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        for (int i = 0;i < getChildCount();i++){
            View v = getChildAt(i);
            LayoutParams lp = (LayoutParams) v.getLayoutParams();
            if(lp.behavior != null){
                lp.behavior.onSizeChanged(this,v,w,h,oldw,oldh);
            }
        }

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    /**
     *  现在模拟的是coordingnatorlayout将Behavior封装到子view的一个过程
     * @param attrs
     * @return
     */
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(),attrs);
    }

    @Override
    public void onGlobalLayout() {
        for (int i = 0;i < getChildCount();i++){
            View v = getChildAt(i);
            LayoutParams lp = (LayoutParams) v.getLayoutParams();
            if(lp.behavior != null){
                lp.behavior.onLayoutFinish(this,v);
            }
        }
    }


    class LayoutParams extends RelativeLayout.LayoutParams{


        public Behavior behavior;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.BeaviorCoordinatorLayout);

            String clazzName = a.getString(R.styleable.BeaviorCoordinatorLayout_layout_behavior);

            behavior = parseBehaivor(c, attrs, clazzName);


            a.recycle();

        }

        private Behavior parseBehaivor(Context context,AttributeSet attrs,String name) {

            if (TextUtils.isEmpty(name)) {
                return null;
            }

            try {
                Class<?> aClass = Class.forName(name, true, context.getClassLoader());
                Constructor c = aClass.getConstructor(new Class[]{Context.class, AttributeSet.class});
                c.setAccessible(true);
                return (Behavior) c.newInstance(context, attrs);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            return null;
        }

        public LayoutParams(int w, int h) {
            super(w, h);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public LayoutParams(RelativeLayout.LayoutParams source) {
            super(source);
        }
    }
}

