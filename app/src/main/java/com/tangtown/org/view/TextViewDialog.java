package com.tangtown.org.view;

import android.os.Bundle;
import android.view.View;

import com.app.org.base.BaseActivity;
import com.app.org.view.dialog.BaseDialogLoading;
import com.github.anzewei.parallaxbacklayout.ParallaxBack;
import com.tangtown.org.R;

/**
 * Created by lixingxing on 2018/3/8.
 */
@ParallaxBack(edge = ParallaxBack.Edge.LEFT,layout = ParallaxBack.Layout.PARALLAX)
public class TextViewDialog extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_dialog);
    }

    public void onBtnClick(View view) {
        switch (view.getId()) {
            case R.id.BaseLoadingDialog:
                BaseDialogLoading baseDialogLoading = new BaseDialogLoading(baseContext);
                baseDialogLoading.show();
                break;
            default:
                break;
        }
    }
}
