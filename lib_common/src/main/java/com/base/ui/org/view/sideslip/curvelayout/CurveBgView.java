package com.base.ui.org.view.sideslip.curvelayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 曲线侧滑菜单背景区域 (曲线背景)
 * Created by lixingxing on 2018/7/25.
 */
public class CurveBgView extends View{

    int offset = 10;
    // 开始点
    // 结束点
    // 控制点  x不动 改变y

    Paint paint;
    Path path;

    public CurveBgView(Context context) {
        this(context,null);
    }

    public CurveBgView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CurveBgView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public void init(){
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        path = new Path();
    }


    /**
     *  根据触摸点调整path内容
     * @param touchY    控制点y坐标
     * @param percent   百分比
     */
    public void setTouchY(float touchY,float percent){
        path.reset();

        // 偏移量
        float offset = getHeight() / 8 ;

        // 开始点
        float beginX = 0;
        float beginY = -offset;
        // 结束点
        float endX = 0;
        float endY = getHeight() + offset;
        // 控制点
        float controlX = getWidth() * percent * 3 / 2;
        float controlY = touchY;

        path.lineTo(beginX,beginY);
        path.quadTo(controlX,controlY,endX,endY);
        path.close();

        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPath(path,paint);
    }

    public void setColor(int color){
        paint.setColor(color);
    }

    /**
     * 背景转换成图片
     * @param color
     */
    public void setColor(Drawable color){
    }

}
