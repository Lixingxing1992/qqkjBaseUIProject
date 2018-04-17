package com.app.org.http

import com.app.org.encryption.BaseEncodeUtil
import com.app.org.utils.BaseLogUtil
import com.app.org.utils.BaseStringUtil
import com.app.org.utils.BaseThreadPoolUtil
import com.google.gson.Gson
import com.google.gson.JsonParser
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.DataOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.ProtocolException
import java.net.URL
import java.util.*


/**
 * Created by Administrator on 2017/6/7.
 */

class BaseHttpUtil {

    private var successMsg = ""
    private var timeOut = 10 * 1000
    private var connectTimeout = 8 * 1000

    /**
     * JSON
     */
    @Synchronized
    fun postJson(jsonParams: JSONObject?, urlPath: String): BaseHttpResultModel? {
        var baseHttpResultModel = BaseHttpResultModel()

        var url: URL?
        var conn: HttpURLConnection?
        try {
            url = URL(urlPath)
            //打开连接
            conn = url.openConnection() as HttpURLConnection
            //设置提交方式
            conn.doOutput = true
            conn.doInput = true
            conn.requestMethod = "POST"
            //post方式不能使用缓存
            conn.useCaches = false
            conn.instanceFollowRedirects = true
            //设置连接超时时间
            conn.connectTimeout = connectTimeout
            //设置读取超时时间
            conn.readTimeout = timeOut
            //配置本次连接的Content-Type
            conn.setRequestProperty("Content-Type", "application/json") //"application/x-www-form-urlencoded")
            //维持长连接
            conn.setRequestProperty("Connection", "Keep-Alive")
            //设置浏览器编码
            conn.setRequestProperty("Charset", "UTF-8")

            val dos = DataOutputStream(conn.outputStream)
            dos.write(jsonParams?.toString()?.toByteArray())
            dos.flush()
            dos.close()

            var code = conn.responseCode
            baseHttpResultModel.responseCode = code

            if (code == 200) {
                //获得服务器端输出流
                baseHttpResultModel.inputStream = conn.inputStream
            }
        } catch (e: Exception) {
            baseHttpResultModel.exception = e
            e.printStackTrace()
        }

        return baseHttpResultModel
    }

    fun setSuccessMsg(successMsg: String): BaseHttpUtil {
        this.successMsg = successMsg
        return this
    }

    fun setTimeOut(timeOut: Int): BaseHttpUtil {
        this.timeOut = timeOut
        return this
    }

    fun setConnectTimeout(connectTimeout: Int): BaseHttpUtil {
        this.connectTimeout = connectTimeout
        return this
    }


    companion object {
        private val CHARSET = "utf-8"
    }

}
