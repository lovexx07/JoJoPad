package com.jojo.pad.ui.activity;

import com.blankj.utilcode.util.EncodeUtils;
import com.blankj.utilcode.util.EncryptUtils;
import com.jojo.pad.R;
import com.jojo.pad.base.BaseAcitivty;
import com.jojo.pad.model.bean.req.TokenBean;

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
//        final boolean islogin = SPUtils.getInstance().getBoolean(SPKey.ACCOUNT_ISLOGIN);
//        final boolean islogin_em = SPUtils.getInstance().getBoolean(SPKey.ACCOUNT_ISLOGIN_EM);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//                    startActivity(new Intent(SplashActivity.this, CompanyLoginActivity.class));
//            }
//        }, 3000);

        TokenBean bean = new TokenBean();
        bean.setAppid("sbVkKkbxwrVIFLr30c");

        byte[] secret = EncodeUtils.base64Encode(EncryptUtils.encryptSHA1ToString("'eDIor4hR'").substring(0, 20));
        bean.setSecret(new String(secret));
        bean.setTimestamp(System.currentTimeMillis());
//        RetrofitManager.getInstance().server.getToken(bean).enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                try {
//                    LogUtils.e(response.body().string());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//            }
//        });
    }

}
