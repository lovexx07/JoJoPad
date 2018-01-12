package com.jojo.pad.model.bean.print;

/**
 * Created by Administrator on 2018/1/12 0012.
 */

public class PrintRecharge {
    private String number;
    private String recharge_fee;
    private String recharge;
    private String send;
    private String recharge_end;
    private String paytype;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRecharge_fee() {
        return recharge_fee;
    }

    public void setRecharge_fee(String recharge_fee) {
        this.recharge_fee = recharge_fee;
    }

    public String getRecharge() {
        return recharge;
    }

    public void setRecharge(String recharge) {
        this.recharge = recharge;
    }

    public String getSend() {
        return send;
    }

    public void setSend(String send) {
        this.send = send;
    }

    public String getRecharge_end() {
        return recharge_end;
    }

    public void setRecharge_end(String recharge_end) {
        this.recharge_end = recharge_end;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }
}
