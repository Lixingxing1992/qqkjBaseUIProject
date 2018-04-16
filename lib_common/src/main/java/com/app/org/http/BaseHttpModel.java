package com.app.org.http;

/**
 * Created by lixingxing on 2018/4/16.
 */
public class BaseHttpModel {
    public String url;
    public Object params;
    public int http_code;
    public int http_type;
    public Class cls;
    public String resultCode;

    public BaseHttpHandlerCallBack baseHttpHandlerCallBack;
    //别的回调
    public Object baseOtherHttpHandlerCallBack;
}
