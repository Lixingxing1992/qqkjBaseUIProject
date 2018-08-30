package com.qqkjbaseui.org.corrdinatorlayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.qqkjbaseui.org.R;

/**
 * Created by lixingxing on 2018/7/23.
 */
public class CoordinatorLayoutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_main);
    }

    public void onBtnClick(View view) {
        switch (view.getId()) {
            case R.id.coordinatorLayout1:
                startActivity(new Intent(this, TestCoordinatorActivity.class).putExtra("code",0));
                break;
            case R.id.coordinatorLayout2:
                break;
            default:
                break;
        }
    }
}
