package com.jojo.pad.model.bean.result;

/**
 * Created by ruifeng on 2018/1/9.
 */

public class MemberSignInBean {
    private String user_id;
    private String operator;
    private String store_id;
    private String store_name;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    @Override
    public String toString() {
        return "MemberSignInBean{" +
                "user_id='" + user_id + '\'' +
                ", operator='" + operator + '\'' +
                ", store_id='" + store_id + '\'' +
                ", store_name='" + store_name + '\'' +
                '}';
    }
}
