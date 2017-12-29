package com.jojo.pad.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.jojo.pad.app.MyApplication;

/**
 * Created by Administrator on 2017/12/27 0027.
 */

public class SharedPreferencesUtil {

    private static SharedPreferences mPreferences;

    private static SharedPreferences getSp() {
        if (mPreferences == null) {
            mPreferences = MyApplication.getInstance().getSharedPreferences("my_sp",
                    Context.MODE_PRIVATE);
        }
        return mPreferences;
    }

    /**
     * 获得boolean类型的值，没有时默认值为false
     */
    public static boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    /**
     * 获得boolean类型的数据
     */
    public static boolean getBoolean(String key,
                                     boolean defValue) {
        SharedPreferences sp = getSp();
        return sp.getBoolean(key, defValue);
    }

    /**
     * 设置boolean类型的值
     */
    public static void putBoolean(String key, boolean value) {
        SharedPreferences sp = getSp();
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * 获得String类型的值，没有时默认值为null
     */
    public static String getString(String key) {
        return getString(key, null);
    }


    /**
     * 获得String类型的数据
     */
    public static String getString(String key, String defValue) {
        SharedPreferences sp = getSp();
        return sp.getString(key, defValue);
    }

    /**
     * 设置String类型的值
     */
    public static void putString(String key, String value) {
        SharedPreferences sp = getSp();
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * 获得int类型的值，没有时默认值为-1
     */
    public static int getInt( String key) {
        return getInt( key, -1);
    }

    /**
     * 获得String类型的数据
     */
    public static int getInt(String key, int defValue) {
        SharedPreferences sp = getSp();
        return sp.getInt(key, defValue);
    }

    /**
     * 设置int类型的值
     */
    public static void putInt(String key, int value) {
        SharedPreferences sp = getSp();
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    /**
     * 获得String类型的数据
     */
    public static long getLong( String key, long defValue) {
        SharedPreferences sp = getSp();
        return sp.getLong(key, defValue);
    }


    public static void clearAllFile() {
        SharedPreferences sp = getSp();
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }
}
