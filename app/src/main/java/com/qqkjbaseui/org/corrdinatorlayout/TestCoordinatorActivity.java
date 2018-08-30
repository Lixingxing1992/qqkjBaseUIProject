package com.qqkjbaseui.org.corrdinatorlayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.qqkjbaseui.org.R;

/**
 * 测试  CoordinatorLayout
 * Created by lixingxing on 2018/7/23.
 */
public class TestCoordinatorActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_test);
        int code = getIntent().getIntExtra("code",0);

    }
}
