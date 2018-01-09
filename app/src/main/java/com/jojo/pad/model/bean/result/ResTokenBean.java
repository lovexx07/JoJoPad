package com.jojo.pad.model.bean.result;


/**
 * Created by Administrator on 2018/1/8 0008.
 */

public class ResTokenBean{
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpired_time() {
        return expired_time;
    }

    public void setExpired_time(long expired_time) {
        this.expired_time = expired_time;
    }

    private long expired_time;

}
