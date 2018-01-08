package com.jojo.pad.model;

/**
 * Created by Administrator on 2018/1/8 0008.
 */

public class BaseBean<T> {
    private int code;
    private T data;
    private String msg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }



    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


}
