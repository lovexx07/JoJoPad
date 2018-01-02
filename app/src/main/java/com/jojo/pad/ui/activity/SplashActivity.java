package com.jojo.pad.ui.activity;

import android.content.Intent;
import android.os.Handler;

import com.blankj.utilcode.util.SPUtils;
import com.jojo.pad.R;
import com.jojo.pad.base.BaseAcitivty;
import com.jojo.pad.constant.SPKey;
import com.jojo.pad.ui.activity.account.CompanyLoginActivity;
import com.jojo.pad.ui.activity.account.EmployeeLoginActivity;

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

    }

    @Override
    public void initData() {
        final boolean islogin = SPUtils.getInstance().getBoolean(SPKey.ACCOUNT_ISLOGIN);
        final boolean islogin_em = SPUtils.getInstance().getBoolean(SPKey.ACCOUNT_ISLOGIN_EM);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (islogin){
                    if (islogin_em) {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    }else {
                        startActivity(new Intent(SplashActivity.this, EmployeeLoginActivity.class));
                    }
                }else {
                    startActivity(new Intent(SplashActivity.this, CompanyLoginActivity.class));
                }
            }
        }, 3000);
    }

}
