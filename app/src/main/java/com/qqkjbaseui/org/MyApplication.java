package com.qqkjbaseui.org;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.base.ui.org.BaseUIManager;
import com.zhy.autolayout.config.AutoLayoutConifg;

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        BaseUIManager.init(getApplicationContext());

        AutoLayoutConifg.getInstance().useDeviceSize().init(this);
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // dex突破65535的限制
        MultiDex.install(this);
    }


}
