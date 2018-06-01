package com.app_res.org.util.http

import android.os.Handler
import com.apkfuns.logutils.LogUtils
import com.app.org.base.BaseApplication
import com.app.org.encryption.BaseEncodeUtil
import com.app.org.http.BaseHttpConfig
import com.app.org.http.BaseHttpInterface
import com.app.org.http.BaseHttpUtil
import com.app.org.utils.BaseDataUtil
import com.app.org.utils.BaseLogUtil
import com.app.org.utils.BaseStringUtil
import com.app.org.utils.BaseThreadPoolUtil
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject

/**
 * 自定义网络请求实现类
 * Created by lixingxing on 2018/4/16.
 */
class HttpUtils @JvmOverloads constructor(isShowLog: Boolean? = true)
    : BaseHttpInterface() {

    internal var tag = System.currentTimeMillis().toString() + ""

    var isShowLog = true

    init {
        tag = System.currentTimeMillis().toString() + ""
        this.isShowLog = isShowLog!!
    }

    override fun initUrl(url: String): BaseHttpInterface {
        baseHttpModel!!.url = url
        if(isShowLog){
             LogUtils.tag(tag + "__httpUtils").e( "请求地址: " + baseHttpModel!!.url)
        }
        return this
    }

    override fun initParams(vararg param: Any): BaseHttpInterface? {
        var params = JSONObject()
        if (param.isNotEmpty() && param.size % 2 == 0) {
            var i = 0
            while (i < param.size - 1) {
                val key = param[i].toString()
                val value = param[i + 1]
                if (value is List<*>) {
                    val string = Gson().toJson(value)
                    val job = JSONArray(string)
                    params!!.put(key, job)
                } else {
                    params!!.put(key, value)
                }
                i += 2
            }
        }
        if(isShowLog)
            LogUtils.tag(tag + "__httpUtils").e("加密前请求参数: $params")
        //将请求参数数据向服务器端发送\
        var jsonParams = JSONObject()
        jsonParams.put("userCode", "qqkj")
        jsonParams.put("passwd", "123456")
        if (!BaseStringUtil.isEmpty(params.toString())) {
            var params = BaseEncodeUtil.ooEncode(params.toString())
            jsonParams.put("data", params)
        }
        baseHttpModel!!.params = jsonParams
        if(isShowLog)
            LogUtils.tag(tag + "__httpUtils").e("加密后请求参数: " + baseHttpModel!!.params)
        return this
    }

    override fun initHttpType(type: Int): BaseHttpInterface? {
        baseHttpModel.http_type = type
        return this
    }

    override fun goHttp(): BaseHttpInterface? {
        var httpHandler: Handler? = null
        var httpHandlerCallBack = baseHttpModel.baseHttpHandlerCallBack

        if (httpHandlerCallBack != null) {
            httpHandler = Handler(httpHandlerCallBack)
        }
        BaseThreadPoolUtil.execute {
            if(BaseApplication.getIns().newWorkType.checkNewCanUse(true)){
                try {
                    if (BaseStringUtil.isEmpty(baseHttpModel.url)) {
                        httpHandlerCallBack?.errorCode = BaseHttpConfig.ErrorCode.Error_Params
                        httpHandler?.obtainMessage(0, httpHandlerCallBack?.errorCode.toString())?.sendToTarget()
                    } else {
                        httpHandlerCallBack?.errorCode = BaseHttpConfig.ErrorCode.Error_Unknow

                        val baseHttpResultModel = baseHttpUtil.request((baseHttpModel!!.params as JSONObject).toString(), baseHttpModel!!.url)

                        if (baseHttpResultModel!!.responseCode == 200 && baseHttpResultModel!!.inputStream != null) {
                            val bytes = BaseDataUtil.inputStream2Bytes(baseHttpResultModel!!.inputStream)
                            val result = String(bytes)
                            if (BaseStringUtil.isEmpty(result)) {
                                httpHandlerCallBack?.errorCode = BaseHttpConfig.ErrorCode.Error_Read
                                httpHandler?.obtainMessage(0, httpHandlerCallBack?.errorCode.toString())?.sendToTarget()
                            } else {
                                if(isShowLog)
                                     LogUtils.tag(tag + "__httpUtils").e( "返回值 = " + result)
                                httpHandlerCallBack?.errorCode = BaseHttpConfig.ErrorCode.Error_Success
                                httpHandler?.obtainMessage(0, result)?.sendToTarget()
                            }
                        } else {
                            if(baseHttpResultModel!!.exception != null){
                                // 请求出现异常等等
                                httpHandlerCallBack?.errorCode = BaseHttpConfig.ErrorCode.Error_ResultException
                                if(isShowLog)
                                     LogUtils.tag(tag + "__httpUtils").e( baseHttpResultModel!!.exception)
                                httpHandler?.obtainMessage(0, httpHandlerCallBack?.errorCode.toString())?.sendToTarget()
                            }else{
                                // 请求结果码错误等等
                                httpHandlerCallBack?.errorCode = BaseHttpConfig.ErrorCode.Error_ResultErrorCode
                                if(isShowLog)
                                     LogUtils.tag(tag + "__httpUtils").e( httpHandlerCallBack?.errorCode.toString()+
                                            ",错误码为: ${baseHttpResultModel!!.responseCode}")
                                httpHandler?.obtainMessage(0, httpHandlerCallBack?.errorCode.toString()+
                                        ",错误码为: ${baseHttpResultModel!!.responseCode}")?.sendToTarget()
                            }
                        }
                    }
                } catch (e: Exception) {
                    // 请求出现异常等等
                    httpHandlerCallBack?.errorCode = BaseHttpConfig.ErrorCode.Error_ResultException
                    if(isShowLog)
                         LogUtils.tag(tag + "__httpUtils").e( e)
                    httpHandler?.obtainMessage(0, httpHandlerCallBack?.errorCode.toString())?.sendToTarget()
                }
            }else{
                httpHandlerCallBack?.errorCode = BaseHttpConfig.ErrorCode.Error_HASNONEW
                if(isShowLog)
                     LogUtils.tag(tag + "__httpUtils").e( httpHandlerCallBack?.errorCode.toString())
                httpHandler?.obtainMessage(0, httpHandlerCallBack?.errorCode.toString())?.sendToTarget()
            }
        }
        return this
    }
}
