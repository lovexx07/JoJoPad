package com.jojo.pad.model.bean.req;

/**
 * Created by ruifeng on 2018/1/8.
 */

public class TokenBean {
    private String appid;
    private String secret;
    private Long timestamp;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
