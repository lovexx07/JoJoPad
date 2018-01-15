package com.jojo.pad.model.http;

import android.app.Activity;

import com.blankj.utilcode.util.EncodeUtils;
import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jojo.pad.app.MyApplication;
import com.jojo.pad.base.BaseBean;
import com.jojo.pad.base.RootBean;
import com.jojo.pad.constant.HttpConstant;
import com.jojo.pad.listener.ResponseListener;
import com.jojo.pad.model.bean.result.ResTokenBean;
import com.jojo.pad.model.http.callback.JsonCallback;
import com.jojo.pad.model.http.callback.StringDialogCallback;
import com.jojo.pad.util.Convert;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.Map;


/**
 * Created by ruifeng on 2016/10/13.
 */
public class BaseHttp {


    public static void getToken(Object tag) {
        String secret = EncodeUtils.base64Encode2String(EncryptUtils.encryptSHA1ToString(HttpConstant.secret).substring(0, 20).toLowerCase().getBytes());
        OkGo.<BaseBean<ResTokenBean>>post(HttpConstant.BaseApi + HttpConstant.Api.token)
                .tag(tag)
                //如果对于部分自签名的https访问不成功，需要加上该控制头
                .params("appid", HttpConstant.appid)
                .params("secret", secret)
                .params("timestamp", System.currentTimeMillis())
                .execute(new JsonCallback<BaseBean<ResTokenBean>>() {
                    @Override
                    public void onError(Response<BaseBean<ResTokenBean>> response) {
                        LogUtils.e("getToken：" + response.code());
                    }

                    @Override
                    public void onSuccess(Response<BaseBean<ResTokenBean>> response) {
                        ResTokenBean tokenBean = response.body().getData();
                        HttpConstant.TOKEN = tokenBean.getToken();
                        HttpConstant.EXPIRED_TIME = tokenBean.getExpired_time();
                    }
                });
    }


    public static void postJson(final String url, final Map<String, String> map, final Activity activity, final ResponseListener listener) {
        OkGo.<String>post(HttpConstant.BaseApi + url)
                .tag(activity)
                .headers("token", HttpConstant.TOKEN)
                .params(map)
                .execute(new StringDialogCallback(activity) {
                    @Override
                    public void onError(Response<String> response) {
                        listener.onError("网络异常!请稍后重试");
                        LogUtils.e("postJson：" + response.toString());
                    }

                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtils.e("postJson：" + response.body());
                        RootBean data = Convert.fromJson(response.body(), RootBean.class);
                        int code = data.getCode();
                        String msg = data.getMsg();
                        if (code == 0) {
                            listener.onSuccess(data.getData());
                        } else {
                            handerError(code, msg, activity, listener);
                        }
                    }
                });

    }
    public static void postJson(final String url,String json, final Activity activity, final ResponseListener listener) {
        OkGo.<String>post(HttpConstant.BaseApi + url)
                .tag(activity)
                .headers("token", HttpConstant.TOKEN)
                .upJson(json)
                .execute(new StringDialogCallback(activity) {
                    @Override
                    public void onError(Response<String> response) {
                        listener.onError("网络异常!请稍后重试");
                        LogUtils.e("postJson：" + response.toString());
                    }

                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtils.e("postJson：" + response.body());
                        RootBean data = Convert.fromJson(response.body(), RootBean.class);
                        int code = data.getCode();
                        String msg = data.getMsg();
                        if (code == 0) {
                            listener.onSuccess(data.getData());
                        } else {
                            handerError(code, msg, activity, listener);
                        }
                    }
                });

    }
    public static void getJson(final String url, final Map<String, String> map, final Activity activity, final ResponseListener listener) {
        OkGo.<String>get(HttpConstant.BaseApi + url)
                .tag(activity)
                .headers("token", HttpConstant.TOKEN)
                .params(map)
                .execute(new StringDialogCallback(activity) {
                    @Override
                    public void onError(Response<String> response) {
                        listener.onError("网络异常!请稍后重试");
                        LogUtils.e("getJson：" + response.toString());
                    }

                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtils.e("getJson：" + response.body().toString());
                        RootBean data = Convert.fromJson(response.body(), RootBean.class);
                        int code = data.getCode();
                        String msg = data.getMsg();
                        if (code == 0) {
                            listener.onSuccess(data.getData());
                        } else {
                            handerError(code, msg, activity, listener);
                        }
                    }
                });

    }
    private static void handerError(int code, String msg, Activity activity, ResponseListener listener) {
        String result = "网络异常请稍后重试";
        switch (code) {
            case CODE.LACK_PARAMS:

                break;
            case CODE.REQUEST_TIMEOUT:

                break;
            case CODE.NO_ACCESS:

                break;
            case CODE.INVALID_TOKEN:
                getToken(MyApplication.getInstance());
                break;
            case CODE.INVALID_BARCODE:
                result = "搜不到商品！";
                break;
            case CODE.INVALID_USER:
                result = "账号未注册！";
                break;
            case CODE.SYSTEM_ERROR:
                break;
            case CODE.SYSTEM_ERROR_2:
                msg = "记账失败";
                break;
            default:
                result = msg;
        }
        listener.onError(result);

    }


    interface CODE {
        int LACK_PARAMS = 1000;//缺少参数
        int REQUEST_TIMEOUT = 1001;//	请求超时
        int NO_ACCESS = 1002;//	没有权限
        int INVALID_TOKEN = 1003;//		无效的token
        int INVALID_USER = 1005;//		无效用户
        int SYSTEM_ERROR = 1006;//		系统错误

        int INVALID_BARCODE = 2108;//		未找到商品
        int SYSTEM_ERROR_2 = 4002;//		记账失败
    }
}
