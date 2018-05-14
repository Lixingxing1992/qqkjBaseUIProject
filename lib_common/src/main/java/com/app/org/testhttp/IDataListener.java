package com.app.org.testhttp;

/**
 * Created by lixingxing on 2018/5/10.
 */
public interface IDataListener<T> {

    void onSuccess(T responseData);
}
