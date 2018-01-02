package com.jojo.pad.app;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * Created by Administrator on 2017/12/27 0027.
 */

public class MyApplication extends Application {
    private static MyApplication myApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        initLogger();
        Utils.init(this);
    }

    public static Application getInstance(){
        return myApplication;
    }
    private void initLogger() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                // (Optional) Whether to show thread info or not. Default true
                .showThreadInfo(false)
                // (Optional) How many method line to show. Default 2
                .methodCount(5)
                // (Optional) Hides internal method calls up to offset. Default 5
                .methodOffset(7)
                // (Optional) Changes the log strategy to print out. Default LogCat
//                .logStrategy(customLog)
                // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .tag("pad")
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
    }
}
