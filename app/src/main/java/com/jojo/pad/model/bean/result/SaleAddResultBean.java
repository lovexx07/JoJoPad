package com.jojo.pad.model.bean.result;

/**
 * Created by ruifeng on 2018/1/12.
 */

public class SaleAddResultBean {
    public SaleAddResultBean() {
    }

    public SaleAddResultBean(String is_retail, String real_money, String order_id, String pay_type, String pay_type_name) {
        this.is_retail = is_retail;
        this.real_money = real_money;
        this.order_id = order_id;
        this.pay_type = pay_type;
        this.pay_type_name = pay_type_name;
    }

    private String is_retail;
    private String real_money;
    private String order_id;
    private String pay_type;
    private String pay_type_name;

    public String getIs_retail() {
        return is_retail;
    }

    public void setIs_retail(String is_retail) {
        this.is_retail = is_retail;
    }

    public String getReal_money() {
        return real_money;
    }

    public void setReal_money(String real_money) {
        this.real_money = real_money;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }

    public String getPay_type_name() {
        return pay_type_name;
    }

    public void setPay_type_name(String pay_type_name) {
        this.pay_type_name = pay_type_name;
    }
}
