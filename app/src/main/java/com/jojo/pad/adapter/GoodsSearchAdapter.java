package com.jojo.pad.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jojo.pad.R;
import com.jojo.pad.model.bean.result.GoodCodeListBean;
import com.jojo.pad.model.bean.result.GoodSearchListBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/3 0003.
 */

public class GoodsSearchAdapter extends BaseQuickAdapter<GoodSearchListBean.GoodsListBean,BaseViewHolder> {
    public GoodsSearchAdapter(@Nullable List<GoodSearchListBean.GoodsListBean> data) {
        super(R.layout.item_search_good_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodSearchListBean.GoodsListBean item) {
        helper.setText(R.id.tv_id,item.getBarcode());
        helper.setText(R.id.tv_name,item.getGoods_name());
        helper.setText(R.id.tv_price,"￥"+item.getGoods_price());
    }
}
