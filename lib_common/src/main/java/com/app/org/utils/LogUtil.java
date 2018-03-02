package com.app.org.utils;

import com.orhanobut.logger.Logger;
import com.orhanobut.logger.Printer;
import com.orhanobut.logger.Settings;

import com.app.org.BuildConfig;

/**
 * Log信息打印  debug模式下才会打印信息
 * Created by lixingxing on 2017/12/28.
 */

public class LogUtil {
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int ASSERT = 7;
    public static final int INFO = 4;
    public static final int VERBOSE = 2;
    public static final int WARN = 5;

    private static final String DEFAULT_TAG = "PRETTYLOGGER";

    //no instance
    private LogUtil() {
    }

    /**
     * It is used to get the settings object in order to change settings
     *
     * @return the settings object
     */
    public static Settings init() {
        return init(DEFAULT_TAG);
    }

    /**
     * It is used to change the tag
     *
     * @param tag is the given string which will be used in Logger as TAG
     */
    public static Settings init(String tag) {
       return Logger.init(tag);
    }

    public static void log(int priority, String tag, String message, Throwable throwable) {
        if(!BuildConfig.DEBUG){
            return;
        }
        Logger.log(priority, tag, message, throwable);
    }

    public static void d(String message, Object... args) {
        if(!BuildConfig.DEBUG){
            return;
        }
        Logger.d(message, args);
    }

    public static void d(Object object) {
        if(!BuildConfig.DEBUG){
            return;
        }
        Logger.d(object);
    }

    public static void e(String message, Object... args) {
        if(!BuildConfig.DEBUG){
            return;
        }
        Logger.e(null, message, args);
    }

    public static void e(Throwable throwable, String message, Object... args) {
        if(!BuildConfig.DEBUG){
            return;
        }
        Logger.e(throwable, message, args);
    }

    public static void i(String message, Object... args) {
        if(!BuildConfig.DEBUG){
            return;
        }
        Logger.i(message, args);
    }

    public static void v(String message, Object... args) {
        if(!BuildConfig.DEBUG){
            return;
        }
        Logger.v(message, args);
    }

    public static void w(String message, Object... args) {
        if(!BuildConfig.DEBUG){
            return;
        }
        Logger.w(message, args);
    }

    public static void wtf(String message, Object... args) {
        if(!BuildConfig.DEBUG){
            return;
        }
        Logger.wtf(message, args);
    }

    /**
     * Formats the json content and print it
     *
     * @param json the json content
     */
    public static void json(String json) {
        if(!BuildConfig.DEBUG){
            return;
        }
        Logger.json(json);
    }

    /**
     * Formats the json content and print it
     *
     * @param xml the xml content
     */
    public static void xml(String xml) {
        if(!BuildConfig.DEBUG){
            return;
        }
        Logger.xml(xml);
    }

}