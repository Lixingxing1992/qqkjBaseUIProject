package com.app.org.http;

import android.os.Handler;
import android.os.Message;

/**
 * Created by lixingxing on 2018/4/16.
 */
public class BaseHttpHandlerCallBack implements Handler.Callback{
    public BaseHttpConfig.ErrorCode errorCode  = BaseHttpConfig.ErrorCode.Error_Success;
    @Override
    public boolean handleMessage(Message message) {
        return false;
    }
}
