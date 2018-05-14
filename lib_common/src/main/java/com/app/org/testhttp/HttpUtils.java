package com.app.org.testhttp;

import com.app.org.http.BaseHttpHandlerCallBack;
import com.app.org.http.BaseHttpInterface;

/**
 * Created by lixingxing on 2018/5/10.
 */
public class HttpUtils extends BaseHttpInterface{
    IHttpService httpService = new JsonHttpService();
    @Override
    public BaseHttpInterface initUrl(String url) {
        httpService.setUrl(url);
        return this;
    }

    @Override
    public BaseHttpInterface initParams(Object... objects) {
        httpService.setRequest(objects.toString().getBytes());
        return this;
    }

    @Override
    public BaseHttpInterface initHttpType(int type) {
        return this;
    }

    @Override
    public BaseHttpInterface goHttp() {
        httpService.setHttpCallBack(new JsonHttpListener(new IDataListener<Object>() {
            @Override
            public void onSuccess(Object responseData) {

            }
        },Object.class));
        httpService.execute();
        return this;
    }
}
