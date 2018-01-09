package com.jojo.pad.base;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/8 0008.
 */

public class BaseBean<T> implements Serializable {
    private static final long serialVersionUID = 5213230387175987834L;
    private int code;
    private String msg;
    private T data;

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
