package com.jojo.pad.evenbean;

/**
 * Created by ruifeng on 2018/1/11.
 */

public class MemberEvenBean {
    private String cid;
    private String cname;
    private String crecharge;
    private boolean cancle;

    public MemberEvenBean(String cid, String cname, String crecharge, boolean cancle) {
        this.cid = cid;
        this.cname = cname;
        this.crecharge = crecharge;
        this.cancle = cancle;
    }

    public boolean isCancle() {
        return cancle;
    }

    public void setCancle(boolean cancle) {
        this.cancle = cancle;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCrecharge() {
        return crecharge;
    }

    public void setCrecharge(String crecharge) {
        this.crecharge = crecharge;
    }
}
