package com.jojo.pad.model.bean.result;

import java.util.List;

/**
 * Created by ruifeng on 2018/1/13.
 */

public class GoodSearchListBean {
    private int page_index;
    private int page_count;
    private String count;
    private List<GoodsListBean> goods_list;

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

    public List<GoodsListBean> getGoods_list() {
        return goods_list;
    }

    public void setGoods_list(List<GoodsListBean> goods_list) {
        this.goods_list = goods_list;
    }

    public static class GoodsListBean {
        private String goods_name;
        private String goods_price;
        private String image;
        private String thumb;
        private String barcode;
        private String gid;
        private String stock;
        private String child_id;
        private String child_name;
        private String parent_id;
        private String parent_name;

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

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
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

        public String getStock() {
            return stock;
        }

        public void setStock(String stock) {
            this.stock = stock;
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
