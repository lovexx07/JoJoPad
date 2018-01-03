package com.jojo.pad.model.bean;

/**
 * Created by Administrator on 2018/1/3 0003.
 */

public class GoodsSearchBean {
    private String id;
    private String name;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    private String price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GoodsSearchBean() {
    }

    public GoodsSearchBean(String id, String name, String price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
