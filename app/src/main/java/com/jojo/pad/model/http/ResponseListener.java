package com.jojo.pad.model.http;

/**
 * Created by ruifeng on 2018/1/8.
 */

public interface ResponseListener<T>  {
    void onSuccess(T data);
    void onError(String result);
}
