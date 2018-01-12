package com.jojo.pad.model.bean.result;

import java.util.List;

/**
 * Created by Administrator on 2018/1/12 0012.
 */

public class SaleListBean {
    private int page_index;
    private int page_count;
    private String count;
    private String total_price;
    private String total_sale;
    private List<SalesRecordListBean> sales_record_list;

    public int getPage_index() {
        return page_index;
    }

    public void setPage_index(int page_index) {
        this.page_index = page_index;
    }

    public int getPage_count() {
        return page_count;
    }

    public void setPage_count(int page_count) {
        this.page_count = page_count;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public String getTotal_sale() {
        return total_sale;
    }

    public void setTotal_sale(String total_sale) {
        this.total_sale = total_sale;
    }

    public List<SalesRecordListBean> getSales_record_list() {
        return sales_record_list;
    }

    public void setSales_record_list(List<SalesRecordListBean> sales_record_list) {
        this.sales_record_list = sales_record_list;
    }

    public static class SalesRecordListBean {
        private String order_id;
        private String member_id;
        private String store_id;
        private String type;
        private String action_id;
        private String prices;
        private String paytime;
        private String serial_num;
        private String name;
        private String user_id;
        private String operator;
        private String cid;
        private String type_name;
        private String short_time;
        private int sale_num;
        private List<SalesListBean> sales_list;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getMember_id() {
            return member_id;
        }

        public void setMember_id(String member_id) {
            this.member_id = member_id;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAction_id() {
            return action_id;
        }

        public void setAction_id(String action_id) {
            this.action_id = action_id;
        }

        public String getPrices() {
            return prices;
        }

        public void setPrices(String prices) {
            this.prices = prices;
        }

        public String getPaytime() {
            return paytime;
        }

        public void setPaytime(String paytime) {
            this.paytime = paytime;
        }

        public String getSerial_num() {
            return serial_num;
        }

        public void setSerial_num(String serial_num) {
            this.serial_num = serial_num;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

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

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public String getShort_time() {
            return short_time;
        }

        public void setShort_time(String short_time) {
            this.short_time = short_time;
        }

        public int getSale_num() {
            return sale_num;
        }

        public void setSale_num(int sale_num) {
            this.sale_num = sale_num;
        }

        public List<SalesListBean> getSales_list() {
            return sales_list;
        }

        public void setSales_list(List<SalesListBean> sales_list) {
            this.sales_list = sales_list;
        }

        public static class SalesListBean {

            private String rec_id;
            private String goods_id;
            private String goods_number;
            private String goods_name;
            private String goods_price;
            private String able_money;
            private String real_money;
            private String discount;
            private String msg;
            private String thumb;
            private String child_id;
            private String child_name;
            private String parent_id;
            private String parent_name;

            public String getRec_id() {
                return rec_id;
            }

            public void setRec_id(String rec_id) {
                this.rec_id = rec_id;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getGoods_number() {
                return goods_number;
            }

            public void setGoods_number(String goods_number) {
                this.goods_number = goods_number;
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

            public String getAble_money() {
                return able_money;
            }

            public void setAble_money(String able_money) {
                this.able_money = able_money;
            }

            public String getReal_money() {
                return real_money;
            }

            public void setReal_money(String real_money) {
                this.real_money = real_money;
            }

            public String getDiscount() {
                return discount;
            }

            public void setDiscount(String discount) {
                this.discount = discount;
            }

            public String getMsg() {
                return msg;
            }

            public void setMsg(String msg) {
                this.msg = msg;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public String getChild_id() {
                return child_id;
            }

            public void setChild_id(String child_id) {
                this.child_id = child_id;
            }

            public String getChild_name() {
                return child_name;
            }

            public void setChild_name(String child_name) {
                this.child_name = child_name;
            }

            public String getParent_id() {
                return parent_id;
            }

            public void setParent_id(String parent_id) {
                this.parent_id = parent_id;
            }

            public String getParent_name() {
                return parent_name;
            }

            public void setParent_name(String parent_name) {
                this.parent_name = parent_name;
            }
        }
    }
}
