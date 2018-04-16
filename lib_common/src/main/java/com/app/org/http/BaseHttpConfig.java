package com.app.org.http;

/**
 * Created by lixingxing on 2018/4/11.
 */
public class BaseHttpConfig {
    public static final int HTTP_TYPE_POST = 0;
    public static final int HTTP_TYPE_GET = 1;
    public static final int HTTP_TYPE_POST_JSON = 2;
    public static final int HTTP_TYPE_GET_JSON = 3;

    public static final int HTTP_TYPE_POST_TANGDAO = 4;


    //错误代码
    public enum ErrorCode {
        Error_Success("请求成功"),
        Error_Params("因为程序原因导致参数传递错误"),
        Error_Unknow("未知错误导致请求失败"),
        Error_TimeOut("服务器连接超时"),
        Error_Read("读取返回结果时异常"),
        Error_Result_Parsr_error("后台程序返回值不符合格式要求"),
        Error_Result_none("未获取到数据"),
        Error_Result_error("获取数据失败");

        private String msg = "";
        ErrorCode(String msg){
            this.msg = msg;
        }
        @Override
        public String toString() {
            return msg;
        }
    }
}
