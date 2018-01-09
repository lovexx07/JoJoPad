package com.jojo.pad.listener;


import org.json.JSONObject;

/**
 * Created by ruifeng on 2018/1/8.
 */

public abstract class ResponseListener {
    public abstract void onSuccess(Object data);
    public  void onError(String result){

    }
}
