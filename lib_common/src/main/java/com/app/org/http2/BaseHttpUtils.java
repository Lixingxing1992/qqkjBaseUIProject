package com.app.org.http2;

import com.app.org.http.BaseHttpConfig;
import com.app.org.http.BaseHttpHandlerCallBack;
import com.app.org.http.BaseHttpModel;

/**
 * Created by lixingxing on 2018/6/1.
 */
public class BaseHttpUtils{

    public static IHttpService iHttpServiceStatic;
    public static void init(IHttpService iHttpServiceStatics){
        iHttpServiceStatic = iHttpServiceStatics;

    }
    public IHttpService iHttpService;
    public BaseHttpModel baseHttpModel;


    public BaseHttpUtils(){
        baseHttpModel = new BaseHttpModel();
    }

    public BaseHttpUtils(BaseHttpModel baseHttpModel) {
        this.baseHttpModel = baseHttpModel;
    }

    public BaseHttpUtils(IHttpService iHttpService) {
        this.iHttpService = iHttpService;
    }

    // 设置请求路径
    public BaseHttpUtils initUrl(String url){
        baseHttpModel.url = url;
        return this;
    }
    // 设置请求参数
    public BaseHttpUtils initParams(Object... objects){
        baseHttpModel.params = objects;
        return this;
    }
    // 设置请求方式 type
    public BaseHttpUtils initRequestType(BaseHttpConfig.RequestType type){
        baseHttpModel.request_type = type;
        return this;
    }

    // 设置请求回调
    public BaseHttpUtils initHttpCallBack(BaseHttpHandlerCallBack baseHttpHandlerCallBack){
        baseHttpModel.baseHttpHandlerCallBack = baseHttpHandlerCallBack;
        return this;
    }
    // 设置请求回调2  正常调用这个方法需要重写  goHttp方法
    public BaseHttpUtils initHttpCallBack(Object baseHttpHandlerCallBack){
        baseHttpModel.baseOtherHttpHandlerCallBack = baseHttpHandlerCallBack;
        return this;
    }
    // 设置返回值的类型
    public BaseHttpUtils initClass(Class tClass){
        baseHttpModel.cls = tClass;
        return this;
    }
    // 设置需要获取的返回值 code
    public BaseHttpUtils initResultCode(String resultCode){
        baseHttpModel.resultCode = resultCode;
        return this;
    }

    public BaseHttpUtils requst(){
        if(iHttpService != null){
            // 如果成员变量有值 --> 有特殊处理操作
            iHttpService.request(baseHttpModel);
        }else  if(iHttpServiceStatic != null){
            // 如果成员变量没有值，静态变量有值 --> 普通操作
            iHttpServiceStatic.request(baseHttpModel);
        }else {

        }
        return this;
    }

}
