package com.qqkjbasepro.org.util.http

import android.os.Handler
import com.app.org.http.BaseHttpConfig
import com.app.org.http.BaseHttpInterface
import com.app.org.utils.BaseDataUtil
import com.app.org.utils.BaseLogUtil
import com.app.org.utils.BaseStringUtil
import com.app.org.utils.BaseThreadPoolUtil
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by lixingxing on 2018/4/16.
 */
class HttpUtil : BaseHttpInterface() {

    internal var tag = System.currentTimeMillis().toString() + ""

    init {
        tag = System.currentTimeMillis().toString() + ""
    }

    override fun initUrl(url: String): BaseHttpInterface {
        baseHttpModel!!.url = url
        return this
    }

    override fun initParams(vararg param: Any): BaseHttpInterface? {
        if (param.isNotEmpty() && param.size % 2 == 0) {
            var params = JSONObject()
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
            baseHttpModel!!.params = params
        } else {
            baseHttpModel!!.params = null
        }
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
            BaseLogUtil.e("baseInterface 加密前", tag + "__baseInterface  : " + baseHttpModel!!.url + "?" + baseHttpModel!!.params)
            if (BaseStringUtil.isEmpty(baseHttpModel.url)) {
                httpHandlerCallBack?.errorCode = BaseHttpConfig.ErrorCode.Error_Params
                httpHandler?.obtainMessage(0,httpHandlerCallBack?.errorCode.toString())?.sendToTarget()
            } else {
                httpHandlerCallBack?.errorCode = BaseHttpConfig.ErrorCode.Error_Unknow

                val inputStream = baseHttpUtil.postJson(baseHttpModel!!.params as JSONObject, baseHttpModel!!.url)
                if (inputStream == null) {
                    // 请求超时等等
                    BaseLogUtil.e("baseInterface", tag + "__baseInterface  : " + baseHttpModel!!.url + "?" + baseHttpModel!!.params + "_____conn is out")
                    httpHandlerCallBack?.errorCode = BaseHttpConfig.ErrorCode.Error_TimeOut
                    httpHandler?.obtainMessage(0,httpHandlerCallBack?.errorCode.toString())?.sendToTarget()
                } else {
                    try {
                        val bytes = BaseDataUtil.inputStream2Bytes(inputStream)
                        val result = String(bytes)//bytes.toString();
                        if (BaseStringUtil.isEmpty(result)) {
                            httpHandlerCallBack?.errorCode = BaseHttpConfig.ErrorCode.Error_Read
                            httpHandler?.obtainMessage(0,httpHandlerCallBack?.errorCode.toString())?.sendToTarget()
                        } else {
                            BaseLogUtil.e("baseInterface", tag + "__baseInterface  result = " + result)
                            httpHandler?.obtainMessage(0, result)?.sendToTarget()
                        }
                    } catch (e: Exception) {
                        BaseLogUtil.e("baseInterface", tag + "__baseInterface  error_  = ",e)
                        httpHandlerCallBack?.errorCode = BaseHttpConfig.ErrorCode.Error_Unknow
                        httpHandler?.obtainMessage(0,httpHandlerCallBack?.errorCode.toString())?.sendToTarget()
                        e.printStackTrace()
                    }
                }
            }
        }
        return this
    }
}
