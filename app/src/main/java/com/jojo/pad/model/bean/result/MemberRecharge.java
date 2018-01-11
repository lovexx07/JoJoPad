package com.jojo.pad.model.bean.result;

import java.util.List;

/**
 * Created by ruifeng on 2018/1/11.
 */

public class MemberRecharge {
    private String cid;
    private String recharge_fee;
    private String last_recharge_date;
    private String total_cost;
    private String total_recharge;
    private String total_edit_fee;
    private List<RechargeLogBean> recharge_log;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getRecharge_fee() {
        return recharge_fee;
    }

    public void setRecharge_fee(String recharge_fee) {
        this.recharge_fee = recharge_fee;
    }

    public String getLast_recharge_date() {
        return last_recharge_date;
    }

    public void setLast_recharge_date(String last_recharge_date) {
        this.last_recharge_date = last_recharge_date;
    }

    public String getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(String total_cost) {
        this.total_cost = total_cost;
    }

    public String getTotal_recharge() {
        return total_recharge;
    }

    public void setTotal_recharge(String total_recharge) {
        this.total_recharge = total_recharge;
    }

    public String getTotal_edit_fee() {
        return total_edit_fee;
    }

    public void setTotal_edit_fee(String total_edit_fee) {
        this.total_edit_fee = total_edit_fee;
    }

    public List<RechargeLogBean> getRecharge_log() {
        return recharge_log;
    }

    public void setRecharge_log(List<RechargeLogBean> recharge_log) {
        this.recharge_log = recharge_log;
    }

    public static class RechargeLogBean {
        private String change_fee;
        private String final_fee;
        private String update_time;
        private String type;
        private String operator;
        private String desc;
        private String type_name;

        public String getChange_fee() {
            return change_fee;
        }

        public void setChange_fee(String change_fee) {
            this.change_fee = change_fee;
        }

        public String getFinal_fee() {
            return final_fee;
        }

        public void setFinal_fee(String final_fee) {
            this.final_fee = final_fee;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getOperator() {
            return operator;
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }
    }
}
