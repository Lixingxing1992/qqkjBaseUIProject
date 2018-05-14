package com.app.org.testhttp;

import java.io.ByteArrayInputStream;

/**
 * Created by lixingxing on 2018/5/10.
 */
public class JsonHttpService implements IHttpService{
    String url;
    byte[] requestData;
    IHttpListener iHttpListener;

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void setRequest(byte[] requestData) {
        this.requestData = requestData;
    }

    @Override
    public void execute() {
        postHttp();
    }

    @Override
    public void setHttpCallBack(IHttpListener httpListener) {
        this.iHttpListener = httpListener;
    }

    public void postHttp(){
//        iHttpListener.onSuccess(new ByteArrayInputStream());
    }
}
