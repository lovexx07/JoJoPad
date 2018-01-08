package com.jojo.pad.model.http;

import com.blankj.utilcode.util.LogUtils;
import com.jojo.pad.model.bean.req.TokenBean;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;


/**
 * Created by ruifeng on 2016/10/13.
 */
public class BaseHttp {
        public static void postJson(final String url, final TokenBean bean, Object tag, final ResponseListener listener) {
//            OkGo.post(url)
//                    .connTimeOut(10000L)
//                    .readTimeOut(10000L)
//                    .tag(tag)
//                    .params("appid",bean.getAppid())
//                    .params("secret",bean.getSecret())
//                    .params("timestamp",bean.getTimestamp())
//                    .execute(new StringCallback() {
//                        @Override
//                        public void onSuccess(String result, Call call, Response response) {
//                            LogUtils.e(result);
//                        }
//
//                        @Override
//                        public void onError(Call call, Response response, Exception e) {
//                            listener.onError("网络异常，请稍后重试！");
//                            e.printStackTrace();
//                        }
//                    });
        }


//    public static void postJson(final String url, final String bean, Object tag, final ResponseListener listener) {
//            OkGo.post(url)
//                    .connTimeOut(10000L)
//                    .readTimeOut(10000L)
//                    .tag(tag)
//                    .upJson(bean)
//                    .execute(new StringCallback() {
//                        @Override
//                        public void onSuccess(String result, Call call, Response response) {
//                            LogUtils.e(result);
//                        }
//
//                        @Override
//                        public void onError(Call call, Response response, Exception e) {
//                            listener.onError("网络异常，请稍后重试！");
//                            e.printStackTrace();
//                        }
//                    });
//        }

}
