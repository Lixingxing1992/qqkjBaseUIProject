package com.app.org.testhttp;

import java.io.InputStream;

/**
 * Created by lixingxing on 2018/5/10.
 */
public interface IHttpListener {
    void onSuccess(InputStream inputStream);
    void onFail();
}
