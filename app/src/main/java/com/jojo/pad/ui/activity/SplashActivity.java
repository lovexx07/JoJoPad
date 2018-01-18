package com.jojo.pad.ui.activity;

import android.content.Intent;
import android.os.Handler;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.jojo.pad.R;
import com.jojo.pad.base.BaseAcitivty;
import com.jojo.pad.constant.SPKey;
import com.jojo.pad.model.http.BaseHttp;
import com.jojo.pad.ui.activity.account.CompanyLoginActivity;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class SplashActivity extends BaseAcitivty {
    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {

    }

    @Override
    public void setListener() {
        String model = "商品名称             单价   数量  ";
        String model1 = "商品名称             ";
        String model2 = "单价   ";
        String model3 = "数量  ";

//            int a = model.getBytes("gb2312").length;
//            int b = model1.getBytes("gb2312").length;
//            int c = model2.getBytes("gb2312").length;
//            int d = model3.getBytes("gb2312").length;

        int a = model.toCharArray().length;
        int b = model1.toCharArray().length;
        int c = model2.toCharArray().length;
        int d = model3.toCharArray().length;

        String aaa = getModel("a", b) + getModel("14", c) + getModel("3", d);
        LogUtils.e(model, aaa);


    }

    private String getModel(String data, int length) {
        StringBuilder sb = new StringBuilder(length);
        sb.append(data);
        for (int i = 0; i < length - data.toCharArray().length; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }


    @Override
    public void initData() {
        final boolean islogin = SPUtils.getInstance().getBoolean(SPKey.ACCOUNT_ISLOGIN);
        final boolean islogin_em = SPUtils.getInstance().getBoolean(SPKey.ACCOUNT_ISLOGIN_EM);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(SplashActivity.this, CompanyLoginActivity.class));
                finish();
            }
        }, 300);

        BaseHttp.getToken(getApplication());
    }
}
