package com.app.org.http;

/**
 * Created by lixingxing on 2018/4/11.
 */
public class BaseHttpConfig {
    public HttpInterface httpInterface;

    public BaseHttpConfig(){
        init();
        httpInterface.initUrl("");
    }


    public void init(){
        httpInterface = new HttpInterface() {
            @Override
            public void initUrl(String url) {

            }

            @Override
            public void initParams(Object... objects) {

            }

            @Override
            public void initHttpType(int type) {

            }

            @Override
            public Object initHttpCallBack() {
                return null;
            }

            @Override
            public void goHttp() {

            }
        };
    }
}
