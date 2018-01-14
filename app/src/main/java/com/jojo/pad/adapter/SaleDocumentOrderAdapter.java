package com.jojo.pad.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jojo.pad.R;
import com.jojo.pad.model.bean.result.SaleListBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/12 0012.
 */

public class SaleDocumentOrderAdapter extends BaseQuickAdapter<SaleListBean.SalesRecordListBean,BaseViewHolder> {
    public SaleDocumentOrderAdapter( @Nullable List<SaleListBean.SalesRecordListBean> data) {
        super(R.layout.item_saledocument_order_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SaleListBean.SalesRecordListBean item) {
        int blue = mContext.getResources().getColor(R.color.blue);
        int black = mContext.getResources().getColor(R.color.gray_80);
        if (item.isIsselect()){
            helper.setBackgroundColor(R.id.ll_root,blue);
            helper.setTextColor(R.id.tv_order_id,Color.WHITE);
            helper.setTextColor(R.id.tv_date,Color.WHITE);
            helper.setTextColor(R.id.tv_sum,Color.WHITE);
        }else {
            helper.setBackgroundColor(R.id.ll_root, Color.WHITE);
            helper.setTextColor(R.id.tv_order_id,black);
            helper.setTextColor(R.id.tv_date,black);
            helper.setTextColor(R.id.tv_sum,blue);
        }
        helper.setText(R.id.tv_order_id,item.getOrder_id());
        helper.setText(R.id.tv_date,item.getPaytime());
        helper.setText(R.id.tv_sum,"￥"+item.getPrices());
    }
}
