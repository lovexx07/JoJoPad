package com.jojo.pad.ui.activity;

import android.view.View;
import android.widget.TextView;

import com.jojo.pad.R;
import com.jojo.pad.base.BaseAcitivty;
import com.jojo.pad.dialog.DatePickDialog;
import com.jojo.pad.listener.ViewClickListener;

import butterknife.BindView;

public class TransferDayActivity extends BaseAcitivty implements View.OnClickListener {


    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_date_start)
    TextView tvDateStart;
    @BindView(R.id.tv_date_end)
    TextView tvDateEnd;
    @BindView(R.id.iv_help)
    TextView ivHelp;
    private ViewClickListener mlistener;
    private boolean isstart;
    @Override
    public int getLayoutId() {
        return R.layout.activity_transfer_day;
    }

    @Override
    public void initView() {

    }

    @Override
    public void setListener() {
        tvDateStart.setOnClickListener(this);
        tvDateEnd.setOnClickListener(this);

        mlistener = new ViewClickListener() {
            @Override
            public void clickListener(String msg, int type) {
                if (isstart){
                    tvDateStart.setText(msg);
                }else {
                    tvDateEnd.setText(msg);
                }
            }
        };
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_date_start:
                DatePickDialog startdatePickDialog = new DatePickDialog.Builder(mContext).setTitle("起始时间").setListener(mlistener).create();
                startdatePickDialog.setCanceledOnTouchOutside(true);
                startdatePickDialog.show();
                isstart = true;
                break;
            case R.id.tv_date_end:
                DatePickDialog enddatePickDialog = new DatePickDialog.Builder(mContext).setTitle("结束时间").setListener(mlistener).create();
                enddatePickDialog.setCanceledOnTouchOutside(true);
                enddatePickDialog.show();
                isstart = false;
                break;
            default:
                break;
        }
    }
}
