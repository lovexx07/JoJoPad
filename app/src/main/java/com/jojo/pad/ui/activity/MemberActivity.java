package com.jojo.pad.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.jojo.pad.R;
import com.jojo.pad.base.BaseAcitivty;
import com.jojo.pad.listener.ViewClickListener;
import com.jojo.pad.widget.SearchView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 会员
 */
public class MemberActivity extends BaseAcitivty implements View.OnClickListener {
    @BindView(R.id.iv_help)
    TextView ivHelp;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.searchview)
    SearchView searchview;
    @BindView(R.id.tv_back)
    TextView tvBack;

    @Override
    public int getLayoutId() {
        return R.layout.activity_member;
    }

    @Override
    public void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void setListener() {
        searchview.setSearchListener(new ViewClickListener() {
            @Override
            public void clickListener(String msg, int type) {

            }
        });
        ivHelp.setOnClickListener(this);
        tvBack.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_help:
                ToastUtils.showShort(R.string.help);
                break;
            case R.id.tv_back:
                finish();
                break;
            default:
                break;
        }
    }

}
