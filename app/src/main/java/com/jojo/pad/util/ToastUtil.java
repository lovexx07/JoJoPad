package com.jojo.pad.util;

import android.text.TextUtils;
import android.widget.Toast;

import com.jojo.pad.app.MyApplication;

/**
 * Created by Administrator on 2017/12/28 0028.
 */

public final class ToastUtil {
    private static Toast getToastInstance() {
        return Toast.makeText( MyApplication.getInstance(), "", Toast.LENGTH_SHORT);
    }
    private static Toast toast;

    public static void show(String msg) {
        if (TextUtils.isEmpty(msg)) {
            LogUtil.e(msg);
            return;
        }
        if (toast == null){
            toast = getToastInstance();
        }
        toast.setText(msg);
        toast.show();
    }


    public static void show(int resid){
        if (toast == null){
            toast = getToastInstance();
        }
        toast.setText(resid);
        toast.show();
    }
}