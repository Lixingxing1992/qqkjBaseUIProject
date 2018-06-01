package com.app.org.http2;

import com.app.org.http.BaseHttpModel;
import com.app.org.testhttp.IHttpListener;

/**
 * Created by lixingxing on 2018/5/10.
 */
public interface IHttpService {
    void request(BaseHttpModel baseHttpModel);


    void setUrl(String url);
    void setRequest(byte[] requestData);
    //进行两个接口的通信
    void setHttpCallBack(IHttpListener httpListener);
    void execute();
}
