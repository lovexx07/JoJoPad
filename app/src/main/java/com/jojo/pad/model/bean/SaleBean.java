package com.jojo.pad.model.bean;

import java.util.List;

/**
 * Created by ruifeng on 2018/1/12.
 */

public class SaleBean {
    private String store_id;
    private String user_id;
    private int type;
    private String cid;
    private List<Data> sale_list;

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public List<Data> getSale_list() {
        return sale_list;
    }

    public void setSale_list(List<Data> sale_list) {
        this.sale_list = sale_list;
    }

    public static class Data{
        private String gid;
        private String goods_number;
        private String msg;
        private String discount ="10";//折扣（不能为空,无打折传10）

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


}
