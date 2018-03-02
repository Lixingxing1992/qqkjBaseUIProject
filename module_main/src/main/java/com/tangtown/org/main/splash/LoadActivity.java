package com.tangtown.org.main.splash;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.app.org.activity.BaseSplashActivity;
import com.app.org.constants.RouterConstans;

/**
 * Created by lixingxing on 2018/1/11.
 */

public class LoadActivity extends BaseSplashActivity{

    @Override
    protected void setContentView() {

    }

    @Override
    protected void onPermissionFinish() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                ARouter.getInstance().build(RouterConstans.MainActivity).navigation();
            }
        },2000);
    }
}
