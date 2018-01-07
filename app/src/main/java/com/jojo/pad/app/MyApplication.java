package com.jojo.pad.app;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by Administrator on 2017/12/27 0027.
 */

public class MyApplication extends Application {
    private static MyApplication myApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        Utils.init(this);
        CrashReport.initCrashReport(getApplicationContext());
    }

    public static Application getInstance(){
        return myApplication;
    }
}
