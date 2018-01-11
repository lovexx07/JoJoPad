package com.jojo.pad.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jojo.pad.R;
import com.jojo.pad.model.bean.result.MemberListBean;

import java.util.List;

/**
 * Created by ruifeng on 2018/1/11.
 */

public class MemberAdapter extends BaseQuickAdapter<MemberListBean.MemberBean,BaseViewHolder> {
    public MemberAdapter( @Nullable List<MemberListBean.MemberBean> data) {
        super(R.layout.item_member_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MemberListBean.MemberBean item) {
        helper.setText(R.id.tv_name,item.getName());
        helper.setText(R.id.tv_phone,item.getPhone());
        helper.setText(R.id.tv_cardnumber,item.getNumber());
        helper.setText(R.id.tv_cash,item.getRecharge_fee());
    }
}
