package com.jojo.pad.ui.activity;

import android.view.View;

import com.jojo.pad.R;
import com.jojo.pad.base.BaseAcitivty;
import com.jojo.pad.widget.PadHeader;

import butterknife.BindView;

/**
 * 交接班
 */
public class TransferActivity extends BaseAcitivty implements View.OnClickListener {
    @BindView(R.id.pheader)
    PadHeader pheader;

    @Override
    public int getLayoutId() {
        return R.layout.activity_transfer;
    }

    @Override
    public void initView() {

    }

    @Override
    public void setListener() {
        pheader.setDateClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_date:
                toActivity(TransferDayActivity.class);
                break;
            default:
                break;
        }
    }
}
