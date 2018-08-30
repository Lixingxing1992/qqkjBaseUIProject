package com.base.ui.org.ui;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import com.base.ui.org.utils.BasePrexUtil;

import java.lang.reflect.Field;

/**
 * 测试版
 * Created by Administrator on 2018/6/7.
 */

public class UIUtils {

    private Context context;

    private static UIUtils utils ;

    public static UIUtils getInstance(Context context){
        if(utils == null){
            utils = new UIUtils(context);
        }
        return utils;
    }


    //参照宽高
    public float STANDARD_WIDTH = 720;
    public float STANDARD_HEIGHT = 1280;

    //当前设备实际宽高
    public float displayMetricsWidth ;
    public float displayMetricsHeight ;

    private  final String DIMEN_CLASS = "com.android.internal.R$dimen";


    private UIUtils(Context context){
        this.context = context;
        //获取状态框信息
        int systemBarHeight = getValue(context,"system_bar_height",48);
        STANDARD_HEIGHT -= systemBarHeight;

        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        //加载当前界面信息
        //高度提取，如果有虚拟按键，那么减去了虚拟按键的长度
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);

        if(displayMetricsWidth == 0.0f || displayMetricsHeight == 0.0f){


            if(displayMetrics.widthPixels > displayMetrics.heightPixels){
                this.displayMetricsWidth = displayMetrics.heightPixels;
                this.displayMetricsHeight = displayMetrics.widthPixels - systemBarHeight;
            }else{
                this.displayMetricsWidth = displayMetrics.widthPixels;
                this.displayMetricsHeight = displayMetrics.heightPixels - systemBarHeight;
            }

        }
    }

    //对外提供系数
    public float getHorizontalScaleValue(){
        return displayMetricsWidth / STANDARD_WIDTH;
    }

    public float getVerticalScaleValue(){
        return displayMetricsHeight / STANDARD_HEIGHT;
    }



    public int getValue(Context context,String systemid,int defValue) {

        try {
            Class<?> clazz = Class.forName(DIMEN_CLASS);
            Object r = clazz.newInstance();
            Field field = clazz.getField(systemid);
            int x = (int) field.get(r);
            return context.getResources().getDimensionPixelOffset(x);

        } catch (Exception e) {
           return defValue;
        }
    }


}
