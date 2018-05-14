package com.app.org.testhttp;

/**
 * Created by lixingxing on 2018/5/10.
 */
public interface IHttpService {
    void setUrl(String url);
    void setRequest(byte[] requestData);
    //进行两个接口的通信
    void setHttpCallBack(IHttpListener httpListener);

    void execute();
}
