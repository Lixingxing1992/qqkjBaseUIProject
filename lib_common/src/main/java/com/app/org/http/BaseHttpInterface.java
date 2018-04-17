package com.app.org.http;

/**
 * Created by lixingxing on 2018/4/11.
 */
public abstract class BaseHttpInterface{
    public BaseHttpModel baseHttpModel = new BaseHttpModel();
    public BaseHttpUtil baseHttpUtil = new BaseHttpUtil();

    // 设置请求路径
    public abstract BaseHttpInterface initUrl(String url);
    // 设置请求参数
    public abstract BaseHttpInterface initParams(Object... objects);
    // 设置请求方式 type
    public abstract BaseHttpInterface initHttpType(int type);
    // 设置请求回调
    public BaseHttpInterface initHttpCallBack(BaseHttpHandlerCallBack baseHttpHandlerCallBack){
        baseHttpModel.baseHttpHandlerCallBack = baseHttpHandlerCallBack;
        return this;
    }
    // 设置请求回调2  正常调用这个方法需要重写  goHttp方法
    public BaseHttpInterface initHttpCallBack(Object baseHttpHandlerCallBack){
        baseHttpModel.baseOtherHttpHandlerCallBack = baseHttpHandlerCallBack;
        return this;
    }


    // 设置返回值的类型
    public BaseHttpInterface initClass(Class tClass){
        baseHttpModel.cls = tClass;
        return this;
    }

    // 设置需要获取的返回值 code
    public BaseHttpInterface initResultCode(String resultCode){
        baseHttpModel.resultCode = resultCode;
        return this;
    }

    // 设置请求的一些配置参数
    public BaseHttpInterface initBaseHttpUtil(BaseHttpUtil baseHttpUtil){
        this.baseHttpUtil = baseHttpUtil;
        return this;
    }

    // 设置请求方法
    public abstract BaseHttpInterface goHttp();
}
