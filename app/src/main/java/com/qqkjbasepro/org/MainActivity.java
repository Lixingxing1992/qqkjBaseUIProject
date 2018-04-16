package com.qqkjbasepro.org;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.app.org.base.BaseActivity;
import com.qqkjbasepro.org.util.TestHttp;
import com.qqkjbasepro.org.view.TextViewDialog;
import com.qqkjbasepro.org.view.TextViewProgress;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void onBtnClick(View view) {
        switch (view.getId()) {
            case R.id.progress:
                startActivity(new Intent(baseContext, TextViewProgress.class));
                break;
            case R.id.dialog:
                startActivity(new Intent(baseContext, TextViewDialog.class));
                break;
            case R.id.http:
                startActivity(new Intent(baseContext, TestHttp.class));
                break;
            default:
                break;
        }
    }

}
