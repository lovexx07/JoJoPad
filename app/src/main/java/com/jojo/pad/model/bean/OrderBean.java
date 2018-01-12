package com.jojo.pad.model.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/2 0002.
 */

public class OrderBean implements Serializable{

    private String goods_name;
    private String goods_price;
    private String barcode;
    private String gid;
    private int count=1;

    public OrderBean() {
    }

    public OrderBean(String goods_name, String goods_price, String barcode, String gid) {
        this.goods_name = goods_name;
        this.goods_price = goods_price;
        this.barcode = barcode;
        this.gid = gid;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(String goods_price) {
        this.goods_price = goods_price;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }
}
