package com.qqkjbaseui.org.sideslip;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.qqkjbaseui.org.R;

/**
 * 侧滑菜单
 * Created by lixingxing on 2018/7/25.
 */
public class SidesLipMenuActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slideslip_main);
    }

    public void onBtnClick(View view) {
        switch (view.getId()) {
            case R.id.sideslipmenu1:
                startActivity(new Intent(this, TestCurveSidesLipLayoutActivity.class).putExtra("code",0));
                break;
            default:
                break;
        }
    }


}
