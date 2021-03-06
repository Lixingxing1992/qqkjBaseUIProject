package com.base.ui.org.animation.scroll;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

public class AnimatorFramelayout extends FrameLayout implements DiscrollInterface {
    public AnimatorFramelayout(@NonNull Context context) {
        super(context);
    }

    public AnimatorFramelayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimatorFramelayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     *  <attr name="discrollve_translation">
     *     <flag name="fromTop" value="0x01" />
     *     <flag name="fromBottom" value="0x02" />
     *     <flag name="fromLeft" value="0x04" />
     *     <flag name="fromRight" value="0x08" />
     *  </attr>
     * 0000000001
     * 0000000010
     * 0000000100
     * 0000001000
     * top|left
     * 0000000001 top
     * 0000000100 left 或运算 |
     * 0000000101
     * 反过来就使用& 与运算
     */
    //保存自定义属性
    //定义很多的自定义属性
    private static final int TRANSLATION_FROM_TOP = 0x01;
    private static final int TRANSLATION_FROM_BOTTOM = 0x02;
    private static final int TRANSLATION_FROM_LEFT = 0x04;
    private static final int TRANSLATION_FROM_RIGHT = 0x08;

    //颜色估值器
    private static ArgbEvaluator sArgbEvaluator = new ArgbEvaluator();
    /**
     * 自定义属性的一些接收的变量
     */
    private int mDiscrollveFromBgColor;//背景颜色变化开始值
    private int mDiscrollveToBgColor;//背景颜色变化结束值
    private boolean mDiscrollveAlpha;//是否需要透明度动画
    private int mDisCrollveTranslation;//平移值
    private boolean mDiscrollveScaleX;//是否需要x轴方向缩放
    private boolean mDiscrollveScaleY;//是否需要y轴方向缩放
    private int mHeight;//本view的高度
    private int mWidth;//宽度



    public void setmDiscrollveFromBgColor(int mDiscrollveFromBgColor) {
        this.mDiscrollveFromBgColor = mDiscrollveFromBgColor;
    }

    public void setmDiscrollveToBgColor(int mDiscrollveToBgColor) {
        this.mDiscrollveToBgColor = mDiscrollveToBgColor;
    }

    public void setmDiscrollveAlpha(boolean mDiscrollveAlpha) {
        this.mDiscrollveAlpha = mDiscrollveAlpha;
    }

    public void setmDisCrollveTranslation(int mDisCrollveTranslation) {
        this.mDisCrollveTranslation = mDisCrollveTranslation;
    }

    public void setmDiscrollveScaleX(boolean mDiscrollveScaleX) {
        this.mDiscrollveScaleX = mDiscrollveScaleX;
    }

    public void setmDiscrollveScaleY(boolean mDiscrollveScaleY) {
        this.mDiscrollveScaleY = mDiscrollveScaleY;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;

    }

    @Override
    public void onDiscroll(float ratio) {



        if(mDiscrollveAlpha){
            setAlpha(ratio);
        }
        if(mDiscrollveScaleX){
            setScaleX(ratio);
        }
        if(mDiscrollveScaleY){
            setScaleY(ratio);
        }

        //平移动画，有四个方向，这四个方向被我们整成了一个属性接受
        if(isTranslationFrom(TRANSLATION_FROM_BOTTOM)){
            setTranslationY(mHeight * (1-ratio));
        }

        if(isTranslationFrom(TRANSLATION_FROM_TOP)){
            setTranslationY(-mHeight * (1-ratio));
        }
        if(isTranslationFrom(TRANSLATION_FROM_LEFT)){
            setTranslationX(-mWidth * (1-ratio));
        }

        if(isTranslationFrom(TRANSLATION_FROM_RIGHT)){
            setTranslationX(mWidth * (1-ratio));
        }

        if(mDiscrollveFromBgColor != -1 && mDiscrollveToBgColor != -1){
            setBackgroundColor((Integer) sArgbEvaluator.evaluate(ratio,mDiscrollveFromBgColor,mDiscrollveToBgColor));
        }

    }

    @Override
    public void onResetDiscroll() {

        if(mDiscrollveAlpha){
            setAlpha(0);
        }
        if(mDiscrollveScaleX){
            setScaleX(0);
        }
        if(mDiscrollveScaleY){
            setScaleY(0);
        }

        //平移动画，有四个方向，这四个方向被我们整成了一个属性接受
        if(isTranslationFrom(TRANSLATION_FROM_BOTTOM)){
            setTranslationY(mHeight);
        }

        if(isTranslationFrom(TRANSLATION_FROM_TOP)){
            setTranslationY(-mHeight);
        }

        if(isTranslationFrom(TRANSLATION_FROM_LEFT)){
            setTranslationX(-mWidth);
        }

        if(isTranslationFrom(TRANSLATION_FROM_RIGHT)){
            setTranslationX(mWidth);
        }

    }

    public boolean isTranslationFrom(int translationMask){
        if(mDisCrollveTranslation == -1){
            return false;
        }
        return (mDisCrollveTranslation & translationMask) == translationMask;
    }

}
