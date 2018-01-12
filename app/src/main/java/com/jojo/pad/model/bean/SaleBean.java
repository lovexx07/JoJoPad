package com.jojo.pad.model.bean;

/**
 * Created by ruifeng on 2018/1/12.
 */

public class SaleBean {
    private String gid;
    private String goods_number;
    private String msg;
    private String discount ="10";//折扣（不能为空,无打折传10）

    public SaleBean() {
    }

    public SaleBean(String gid, String goods_number, String msg) {
        this.gid = gid;
        this.goods_number = goods_number;
        this.msg = msg;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getGoods_number() {
        return goods_number;
    }

    public void setGoods_number(String goods_number) {
        this.goods_number = goods_number;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
}
