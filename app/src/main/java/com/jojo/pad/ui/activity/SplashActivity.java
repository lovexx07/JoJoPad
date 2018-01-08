package com.jojo.pad.ui.activity;

import android.util.Base64;

import com.blankj.utilcode.util.EncodeUtils;
import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.LogUtils;
import com.jojo.pad.R;
import com.jojo.pad.base.BaseAcitivty;
import com.jojo.pad.model.BaseBean;
import com.jojo.pad.model.DialogCallback;
import com.jojo.pad.model.StringDialogCallback;
import com.jojo.pad.model.bean.req.TokenBean;
import com.jojo.pad.model.bean.result.ResTokenBean;
import com.jojo.pad.model.http.APIService;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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


        String s1 = EncryptUtils.encryptSHA1ToString("eDIor4hR").substring(0, 20);
        String secret = Base64.encodeToString(s1.getBytes(), Base64.NO_PADDING);

        bean.setSecret(secret);
        bean.setTimestamp(System.currentTimeMillis());

        LogUtils.e(secret,"ZjAxOTgyYzVkZDhjMjNkMzBmOTM=");
        OkGo.<BaseBean<ResTokenBean>>post(APIService.BaseApi + "Public/getToken")
                .tag(this)
                //如果对于部分自签名的https访问不成功，需要加上该控制头
                .headers("Connection", "close")
                .headers("Connection", "close")
                .params("appid", "sbVkKkbxwrVIFLr30c")
                .params("secret", "ZjAxOTgyYzVkZDhjMjNkMzBmOTM=")
                .params("timestamp", System.currentTimeMillis())
                .isMultipart(true)
                .execute(new DialogCallback<BaseBean<ResTokenBean>>(activity) {
                    @Override
                    public void onError(Response<BaseBean<ResTokenBean>> response) {
                    }

                    @Override
                    public void onSuccess(Response<BaseBean<ResTokenBean>> response) {
                    }
                });
    }

    private static byte[] hashTemplate(final byte[] data, final String algorithm) {
        if (data == null || data.length <= 0) return null;
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(data);
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
