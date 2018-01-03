package com.jojo.pad.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jojo.pad.R;
import com.jojo.pad.model.bean.GoodsSearchBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/3 0003.
 */

public class GoodsSearchAdapter extends BaseQuickAdapter<GoodsSearchBean,BaseViewHolder> {
    public GoodsSearchAdapter(@Nullable List<GoodsSearchBean> data) {
        super(R.layout.item_search_good_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsSearchBean item) {
        helper.setText(R.id.tv_id,item.getId());
        helper.setText(R.id.tv_name,item.getName());
        helper.setText(R.id.tv_price,item.getPrice());
    }
}
