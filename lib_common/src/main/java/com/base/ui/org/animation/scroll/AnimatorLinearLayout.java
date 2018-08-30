package com.base.ui.org.animation.scroll;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.base.ui.org.R;

public class AnimatorLinearLayout extends LinearLayout{
    public AnimatorLinearLayout(Context context) {
        this(context,null);
    }

    public AnimatorLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AnimatorLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
    }


    //从UI绘制流程上来讲，


    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new AnimatorLayoutParams(getContext(),attrs);
    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        AnimatorLayoutParams layoutParams = (AnimatorLayoutParams) params;

       if(!isDiscroll(layoutParams)){
           super.addView(child,params);
       }else{
           AnimatorFramelayout fremalayout = new AnimatorFramelayout(getContext());
          
           fremalayout.addView(child);
           fremalayout.setmDiscrollveAlpha(layoutParams.mDiscrollveAlpha);
           fremalayout.setmDiscrollveFromBgColor(layoutParams.mDiscrollveFromBgColor);
           fremalayout.setmDiscrollveScaleX(layoutParams.mDiscrollveScaleX);
           fremalayout.setmDiscrollveScaleY(layoutParams.mDiscrollveScaleY);
           fremalayout.setmDiscrollveToBgColor(layoutParams.mDiscrollveToBgColor);
           fremalayout.setmDisCrollveTranslation(layoutParams.mDisCrollveTranslation);
           super.addView(fremalayout,layoutParams);

       }



    }

    private boolean isDiscroll(AnimatorLayoutParams layoutParams) {

        return layoutParams.mDiscrollveAlpha ||
                layoutParams.mDiscrollveScaleX ||
                layoutParams.mDiscrollveScaleY ||
                layoutParams.mDisCrollveTranslation != -1 ||
                (layoutParams.mDiscrollveFromBgColor != -1 && layoutParams.mDiscrollveToBgColor != -1);
    }


    public class AnimatorLayoutParams extends LayoutParams{


        public boolean mDiscrollveAlpha;
        public boolean mDiscrollveScaleX;
        public boolean mDiscrollveScaleY;
        public int mDisCrollveTranslation;
        public int mDiscrollveFromBgColor;
        public int mDiscrollveToBgColor;

        public AnimatorLayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);

            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.DiscrollView_LayoutParams);
            //没有传属性过来,给默认值FALSE
            mDiscrollveAlpha = a.getBoolean(R.styleable.DiscrollView_LayoutParams_discrollve_alpha, false);
            mDiscrollveScaleX = a.getBoolean(R.styleable.DiscrollView_LayoutParams_discrollve_scaleX, false);
            mDiscrollveScaleY = a.getBoolean(R.styleable.DiscrollView_LayoutParams_discrollve_scaleY, false);
            mDisCrollveTranslation = a.getInt(R.styleable.DiscrollView_LayoutParams_discrollve_translation, -1);
            mDiscrollveFromBgColor = a.getColor(R.styleable.DiscrollView_LayoutParams_discrollve_fromBgColor, -1);
            mDiscrollveToBgColor = a.getColor(R.styleable.DiscrollView_LayoutParams_discrollve_toBgColor, -1);
            a.recycle();
        }
    }

}



