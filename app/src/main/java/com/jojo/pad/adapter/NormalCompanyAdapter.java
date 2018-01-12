package com.jojo.pad.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jojo.pad.R;
import com.jojo.pad.evenbean.OrderListRefreshEvent;
import com.jojo.pad.model.bean.OrderBean;
import com.jojo.pad.util.PriceUtil;
import com.jojo.pad.widget.NumberAddSubView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by ruifeng on 2018/1/12.
 */

public class NormalCompanyAdapter extends BaseQuickAdapter<OrderBean,BaseViewHolder> {
    public NormalCompanyAdapter( @Nullable List<OrderBean> data) {
        super(R.layout.order_item_layout, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final OrderBean item) {
        helper.setText(R.id.tv_id,item.getBarcode());
        helper.setText(R.id.tv_name,item.getGoods_name());
        helper.setText(R.id.tv_price,item.getGoods_price());
        helper.setText(R.id.tv_discount,"");

        NumberAddSubView numberAddSubView = helper.getView(R.id.nas_count);
        numberAddSubView.setOnButtonClickListenter(new NumberAddSubView.OnButtonClickListenter() {
            @Override
            public void onButtonAddClick(View view, int value) {
                item.setCount(value);
                setPrice(item, helper);
            }
            @Override
            public void onButtonSubClick(View view, int value) {
                if (value == 0){
                    item.setCount(0);
                    EventBus.getDefault().post(item);
                }else {
                    item.setCount(value);
                    setPrice(item, helper);
                }
            }
        });
        setPrice(item, helper);
    }

    private void setPrice(OrderBean item, BaseViewHolder helper) {
        helper.setText(R.id.tv_sum, PriceUtil.getSumMoney(item.getGoods_price(),item.getCount()));
        EventBus.getDefault().post(new OrderListRefreshEvent());
    }
}
