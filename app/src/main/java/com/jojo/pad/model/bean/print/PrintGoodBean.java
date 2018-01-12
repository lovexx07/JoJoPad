package com.jojo.pad.model.bean.print;

import com.jojo.pad.model.bean.OrderBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/7 0007.
 */

public class PrintGoodBean {
    private int count;
    private double sum;
    private String real_money;
    private String order_id;
    private String pay_type_name;
    private List<OrderBean> datas;


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
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

    public String getPay_type_name() {
        return pay_type_name;
    }

    public void setPay_type_name(String pay_type_name) {
        this.pay_type_name = pay_type_name;
    }

    public List<OrderBean> getDatas() {
        return datas;
    }

    public void setDatas(List<OrderBean> datas) {
        this.datas = datas;
    }
}
