package com.app.org.testhttp;

import android.os.Handler;

import com.app.org.utils.BaseDataUtil;

import java.io.InputStream;

/**
 * Created by lixingxing on 2018/5/10.
 */
public class JsonHttpListener<T> implements IHttpListener{

    IDataListener<T> dataListener;
    Class<T> responceClass;
    Handler handler = new Handler();

    public JsonHttpListener(IDataListener<T> dataListener, Class<T> responceClass) {
        this.dataListener = dataListener;
        this.responceClass = responceClass;
    }

    @Override
    public void onSuccess(InputStream inputStream) {
        String content = BaseDataUtil.inputStream2String(inputStream,"UTF-8");
        handler.post(new Runnable() {
            @Override
            public void run() {
//                dataListener.onSuccess(content);
            }
        });
    }

    @Override
    public void onFail() {

    }
}
