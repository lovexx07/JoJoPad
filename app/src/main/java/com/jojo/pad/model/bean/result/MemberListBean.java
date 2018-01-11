package com.jojo.pad.model.bean.result;

import java.util.List;

/**
 * Created by ruifeng on 2018/1/11.
 */

public class MemberListBean {
    private int page_index;
    private int page_count;
    private int count;
    private List<MemberBean> customer_list;

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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<MemberBean> getCustomer_list() {
        return customer_list;
    }

    public void setCustomer_list(List<MemberBean> customer_list) {
        this.customer_list = customer_list;
    }

    public static class MemberBean{
        private String cid;
        private String number;
        private String name;
        private String phone;
        private String group_name;
        private String recharge_fee;

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getGroup_name() {
            return group_name;
        }

        public void setGroup_name(String group_name) {
            this.group_name = group_name;
        }

        public String getRecharge_fee() {
            return recharge_fee;
        }

        public void setRecharge_fee(String recharge_fee) {
            this.recharge_fee = recharge_fee;
        }

        public List<Tag> getTag() {
            return tag;
        }

        public void setTag(List<Tag> tag) {
            this.tag = tag;
        }

        private List<Tag> tag;
        public static class Tag{
            private String tag_id;
            private String tag_name;
            private String tag_color;

            public String getTag_id() {
                return tag_id;
            }

            public void setTag_id(String tag_id) {
                this.tag_id = tag_id;
            }

            public String getTag_name() {
                return tag_name;
            }

            public void setTag_name(String tag_name) {
                this.tag_name = tag_name;
            }

            public String getTag_color() {
                return tag_color;
            }

            public void setTag_color(String tag_color) {
                this.tag_color = tag_color;
            }
        }
    }


}
