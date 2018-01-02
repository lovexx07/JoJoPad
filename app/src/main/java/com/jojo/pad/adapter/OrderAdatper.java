package com.jojo.pad.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jojo.pad.R;
import com.jojo.pad.model.bean.OrderBean;
import com.jojo.pad.widget.NumberAddSubView;

import java.util.List;

/**
 * Created by Administrator on 2018/1/2 0002.
 */

public class OrderAdatper extends BaseQuickAdapter<OrderBean,BaseViewHolder> {
    public OrderAdatper(@Nullable List<OrderBean> data) {
        super(R.layout.order_item_layout, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, final OrderBean item) {
        helper.setText(R.id.tv_id,item.getItemId());
        helper.setText(R.id.tv_name,item.getName());
        helper.setText(R.id.tv_price,item.getPrice()+"");
        helper.setText(R.id.tv_discount,item.getDiscount());

        NumberAddSubView numberAddSubView = helper.getView(R.id.nas_count);
        numberAddSubView.setOnButtonClickListenter(new NumberAddSubView.OnButtonClickListenter() {
            @Override
            public void onButtonAddClick(View view, int value) {
                item.setCount(value);
            }
            @Override
            public void onButtonSubClick(View view, int value) {
                if (value == 0){
                    ToastUtils.showShort("为0");
                }else {
                    item.setCount(value);
                }
            }
        });
        int discount = 100;
        try{
            discount = Integer.getInteger(item.getDiscount());
        }catch (NumberFormatException e){
            LogUtils.e("OrderAdatper:"+e);
        }
        double sum = item.getPrice() * item.getCount()  * discount /100;
        helper.setText(R.id.tv_sum,sum+"");
    }
}
