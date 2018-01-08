package com.jojo.pad.model.http;

/**
 * Created by ruifeng on 2018/1/8.
 */

public interface ResponseListener {
    void onSuccess(String result);
    void onError(String result);
}
