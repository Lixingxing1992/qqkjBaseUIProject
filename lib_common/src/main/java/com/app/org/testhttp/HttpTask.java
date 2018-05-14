package com.app.org.testhttp;

/**
 * Created by lixingxing on 2018/5/10.
 */
public class HttpTask<T> implements Runnable{

    private IHttpService httpService;
    private IHttpListener httpListener;

    public<T> HttpTask(String url,T requestInfo,IHttpService iHttpService,IHttpListener iHttpListener){
        this.httpService = iHttpService;
        this.httpListener = iHttpListener;
        httpService.setUrl(url);
        httpService.setHttpCallBack(httpListener);

        // 设置请求参数 json
        if(requestInfo != null){
            //把请求参数
        }
    }

    @Override
    public void run() {

    }
}
