package com.base.ui.org.animation.scroll;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;

public class AnimatorScrollView extends ScrollView {
    public AnimatorScrollView(Context context) {
        super(context);
    }

    public AnimatorScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimatorScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    AnimatorLinearLayout mContent;

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mContent = (AnimatorLinearLayout) getChildAt(0);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        //实际上，我们开发这种框架，核心的一点是从系统控件上面去获取自定义属性
        //获取到自定义属性之后，我们就能够对自己的动画业务进行处理

        //到这一节位置，是否可以调用到动画了
        //监听滑动了多少距离
        //算出一个百分比出来

        for (int i = 0;i < mContent.getChildCount();i++){
            View child =  mContent.getChildAt(i);



            if(!(child instanceof DiscrollInterface)){
                continue;
            }

            DiscrollInterface discrollInterface = (DiscrollInterface) child;

            //1.得到当前滑动出来的距离
            int childTop = child.getTop();
            int scrollHeight = getHeight();
            int absoluteTop = childTop - t;



            if(absoluteTop <= scrollHeight){
                int visibleHeight = scrollHeight - absoluteTop;

                float radio = visibleHeight / (float)child.getHeight();

                discrollInterface.onDiscroll(clamp(radio,1,0));


                Log.i("barry","childTop:"+childTop);
                Log.i("barry","scrollHeight:"+scrollHeight);
                Log.i("barry","absoluteTop:"+absoluteTop);
                Log.i("barry","t:"+t);
                Log.i("barry","visibleHeight:"+visibleHeight);
                Log.i("barry","radio:"+radio);

            }else{
                discrollInterface.onResetDiscroll();
            }


        }


    }

    private float clamp(float value,float max ,float min) {
        return Math.max(Math.min(value,max),min);
    }
}
