package com.jojo.pad.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jojo.pad.R;
import com.jojo.pad.model.bean.result.SaleListBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/12 0012.
 */

public class SaleDocumentGoodsAdapter extends BaseQuickAdapter<SaleListBean.SalesRecordListBean.SalesListBean,BaseViewHolder> {
    public SaleDocumentGoodsAdapter(@Nullable List<SaleListBean.SalesRecordListBean.SalesListBean> data) {
        super(R.layout.item_saledocument_good_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SaleListBean.SalesRecordListBean.SalesListBean item) {
        helper.setText(R.id.tv_name,item.getGoods_name());
        helper.setText(R.id.tv_count,item.getGoods_number());
        helper.setText(R.id.tv_price,item.getGoods_price());
        helper.setText(R.id.tv_sum,item.getAble_money());
    }
}
