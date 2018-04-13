package com.app.org.http;

/**
 * Created by lixingxing on 2018/4/11.
 */
public interface HttpInterface{

    // 设置请求路径
    public void initUrl(String url);
    // 设置请求参数
    public void initParams(Object... objects);
    // 设置请求方式 type
    public void initHttpType(int type);
    // 设置请求回调
    public Object initHttpCallBack();

    // 设置请求方法
    public void goHttp();
}
