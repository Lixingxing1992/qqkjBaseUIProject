package com.qqkjbaseui.org.animation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.qqkjbaseui.org.R;
import com.qqkjbaseui.org.animation.path.TestPathActivity;

/**
 * Created by lixingxing on 2018/7/11.
 */
public class AnimationActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_main);
    }

    public void onBtnClick(View view) {
        switch (view.getId()) {
            case R.id.animation1:
                startActivity(new Intent(this,TestScrollAminationActivity.class));
                break;
            case R.id.animationPath:
                startActivity(new Intent(this,TestPathActivity.class));
                break;
            default:
                break;
        }
    }

}
