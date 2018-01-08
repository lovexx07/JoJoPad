package com.jojo.pad.model.http;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Response;

/**
 * Created by ruifeng on 2016/10/13.
 */
public class BaseHttp {

    //tag 请求撤回标志
    public static void postJson(final String url, final String content, Object tag, final ResponseListener listener) {
//        OkGo.post(url)
//                .tag(tag)
//                .upString(content, MediaType.parse("application/json; charset=utf-8"))
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(String result, Call call, Response response) {
//                        listener.onSuccess(result);
//                    }
//
//                    @Override
//                    public void onError(Call call, Response response, Exception e) {
////                        handleError(e, listener);
//                        listener.onError("网络异常，请稍后重试！");
//                    }
//                });
    }
}
