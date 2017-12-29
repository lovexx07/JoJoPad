package com.jojo.pad.util;


import com.orhanobut.logger.Logger;

/**
 * Created by Administrator on 2017/12/27 0027.
 */

public class LogUtil {
    public static void e(Object object){
        if (object == null){
            Logger.e("传了一个空！");
        }else {
            Logger.e(object.toString());
        }
    }
    public static void d(Object object){
        if (object == null){
            Logger.e("传了一个空！");
        }else {
            Logger.d(object.toString());
        }
    }
    public static void i(Object object){
        if (object == null){
            Logger.e("传了一个空！");
        }else {
            Logger.i(object.toString());
        }
    }
}
