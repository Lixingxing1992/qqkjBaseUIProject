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
    fun postJson(jsonParam: JSONObject?, urlPath: String): InputStream? {
        var url: URL?
        var conn: HttpURLConnection?
        var inputStream: InputStream? = null
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
            //将请求参数数据向服务器端发送\
            var jsonParams = JSONObject()
            jsonParams.put("userCode", "qqkj")
            jsonParams.put("passwd", "123456")
            if (jsonParam != null) {
                var params = BaseEncodeUtil.ooEncode(jsonParam.toString())
                jsonParams.put("data", params)
            }
            dos.write(jsonParams.toString().toByteArray())
            dos.flush()
            dos.close()
            if (conn.responseCode == 200) {
                //获得服务器端输出流
                inputStream = conn.inputStream
            } else {
                BaseLogUtil.e("baseInterface error", "errorUrl = " + urlPath + " __errorCode = " + conn.responseCode)
            }
        } catch (e: MalformedURLException) {
            // 连接路径不对
            e.printStackTrace()
        } catch (e: ProtocolException) {
            // 协议错误
            e.printStackTrace()
        } catch (e: IOException) {
            // 连接异常
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return inputStream
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
