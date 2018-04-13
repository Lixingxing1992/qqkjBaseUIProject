package com.app.org.http

import com.app.org.encryption.BaseEncodeUtil
import com.app.org.utils.BaseLogUtil
import com.app.org.utils.BaseStringUtil
import com.google.gson.Gson
import com.google.gson.JsonParser
import org.json.JSONArray
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

class HttpUtils {
    /**
     * JSON
     */
    @Synchronized fun postJson(jsonParam: JSONObject?, urlPath: String): InputStream? {
        var url: URL? = null
        var conn: HttpURLConnection? = null
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
            jsonParams.put("userCode","qqkj")
            jsonParams.put("passwd","123456")
            if(jsonParam != null){
                var params = BaseEncodeUtil.ooEncode(jsonParam.toString())
                jsonParams.put("data",params)
            }
            BaseLogUtil.e("baseInterface 加密后:", tag + "__baseInterface  post__" + mode.toString() + "__: " + url + "?" + jsonParams.toString())
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

    @Synchronized fun getJson(urlPath: String): InputStream? {
        var url: URL? = null
        var conn: HttpURLConnection? = null
        var inputStream: InputStream? = null
        try {
            url = URL(urlPath)
            //打开连接
            conn = url.openConnection() as HttpURLConnection
            //设置提交方式
            conn.doOutput = true
            conn.doInput = true
            conn.requestMethod = "GET"
            conn.useCaches = false
            conn.instanceFollowRedirects = true
            //设置连接超时时间
            conn.connectTimeout = connectTimeout
            //设置读取超时时间
            conn.readTimeout = timeOut

            BaseLogUtil.e("baseInterface get请求", tag + "__baseInterface  get__" + mode.toString() + "__: " + url)
            if (conn.responseCode == 200) {
                //获得服务器端输出流
                inputStream = conn.inputStream
            } else {
                BaseLogUtil.e("baseInterface error", "errorUrl = " + urlPath + " __ errorCode = " + conn.responseCode)
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

    fun <T> getObject(jsonString: String, cls: Class<T>): T? {
        var t: T? = null
        try {
            val gson = Gson()
            t = gson.fromJson(jsonString, cls)
        } catch (e: Exception) {
            // TODO: handle exception
            e.printStackTrace()
        }
        return t
    }

    fun <T> getObjectList(jsonString: String, cls: Class<T>): ArrayList<T> {
        val list = ArrayList<T>()
        val gson = Gson()
        val parser = JsonParser()
        val Jarray = parser.parse(jsonString).asJsonArray
        for (obj in Jarray) {
            var t: T = gson.fromJson(obj, cls)
            list.add(t)
        }
        return list
    }


    private var successMsg = ""
    private var url = ""
    private var params: JSONObject? = null
    private var cls: Class<*>? = null
    private var resultCode:String? = null
    private var mode = Mode.Flag
    private var timeOut = 10 * 1000
    private var connectTimeout = 8 * 1000

    internal var tag = System.currentTimeMillis().toString() + ""

    init {
        tag = System.currentTimeMillis().toString() + ""
    }

    enum class Mode constructor(values: String) {
        List("List"), // 结果是个列表
        Object("Object"), // 结果是个对象
        Flag("Flag"),   //  结果判断是否
        SingleParams("SingleParams"), // data : {xx : xx}
        JsonObject("JsonObject")// data : {xx : xx, xx2 : xx}
        ;

        private var value = ""

        init {
            value = values
        }

        override fun toString(): String {
            return value
        }
    }

    fun setSuccessMsg(successMsg : String) : HttpUtils{
        this.successMsg = successMsg
        return this
    }

    fun setUrl(url: String): HttpUtils {
        if (!BaseStringUtil.checkEmpty(url)) {
            this.url = url
        } else {
            this.url = ""
        }
        return this
    }

    fun setParams(vararg param: Any): HttpUtils {
        if (param.size > 0 && param.size % 2 == 0) {
            this.params = JSONObject()
            var i = 0
            while (i < param.size - 1) {
                val key = param[i].toString()
                val value = param[i + 1]
                if(value is List<*>){
                    val string = Gson().toJson(value)
                    val job = JSONArray(string)
                    params!!.put(key, job)
                }else{
                    params!!.put(key, value)
                }
                i += 2
            }
        } else {
            this.params = null
        }

        return this
    }

    fun setMode(mode: Mode): HttpUtils {
        this.mode = mode
        return this
    }

    fun setClass(cls: Class<*>): HttpUtils {
        this.cls = cls
        return this
    }

    fun setResultCode(resultCode :String): HttpUtils{
        this.resultCode = resultCode
        return this
    }

    fun setTimeOut(timeOut: Int): HttpUtils {
        this.timeOut = timeOut
        return this
    }

    fun setConnectTimeout(connectTimeout: Int): HttpUtils {
        this.connectTimeout = connectTimeout
        return this
    }


    companion object {
        private val CHARSET = "utf-8"
    }

}
