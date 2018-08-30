package com.qqkjbaseui.org.auto;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.qqkjbaseui.org.R;
import com.qqkjbaseui.org.animation.AnimationActivity;
import com.qqkjbaseui.org.recyleview.TestRecyleTouchActivity;
import com.zhy.autolayout.AutoLayoutActivity;

public class TestAutoActivity extends AutoLayoutActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_auto);
    }

}
