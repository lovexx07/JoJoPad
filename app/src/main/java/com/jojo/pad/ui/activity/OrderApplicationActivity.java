package com.jojo.pad.ui.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jojo.pad.R;
import com.jojo.pad.base.BaseAcitivty;
import com.jojo.pad.constant.MenuItem;

import butterknife.BindView;

public class OrderApplicationActivity extends BaseAcitivty implements View.OnClickListener {
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_order_id)
    TextView tvOrderId;
    @BindView(R.id.iv_help)
    TextView ivHelp;
    @BindView(R.id.recyclerview_order)
    RecyclerView recyclerviewOrder;
    @BindView(R.id.tv_remarks)
    TextView tvRemarks;
    @BindView(R.id.recyclerview_good)
    RecyclerView recyclerviewGood;
    @BindView(R.id.tv_sum)
    TextView tvSum;
    @BindView(R.id.ll_new_order)
    LinearLayout llNewOrder;
    @BindView(R.id.tv_replay)
    TextView tvReplay;
    @BindView(R.id.tv_submit_again)
    TextView tvSubmitAgain;
    @BindView(R.id.tv_print)
    TextView tvPrint;

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_application;
    }

    @Override
    public void initView() {

    }

    @Override
    public void setListener() {
        llNewOrder.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_new_order:
                toActivity(GoodsManageActivity.class,"type",MenuItem.orderApplication);
                break;
            default:
                break;
        }
    }
}
