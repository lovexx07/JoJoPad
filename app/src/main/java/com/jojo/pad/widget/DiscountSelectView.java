package com.jojo.pad.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jojo.pad.R;
import com.jojo.pad.constant.Constant;
import com.jojo.pad.listener.ViewClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ruifeng on 2018/1/10.
 */

public class DiscountSelectView extends LinearLayout implements View.OnClickListener {
    @BindView(R.id.tv_95)
    Button tv95;
    @BindView(R.id.tv_90)
    Button tv90;
    @BindView(R.id.tv_88)
    Button tv88;
    @BindView(R.id.tv_85)
    Button tv85;
    @BindView(R.id.tv_80)
    Button tv80;
    @BindView(R.id.tv_75)
    Button tv75;
    @BindView(R.id.tv_70)
    Button tv70;
    @BindView(R.id.tv_60)
    Button tv60;
    @BindView(R.id.tv_50)
    Button tv50;
    @BindView(R.id.tv_00)
    Button tv00;
    @BindView(R.id.tv_zero)
    Button tvZero;
    private Context context;
    private ViewClickListener listener;

    public void setViewClickListener(ViewClickListener listener) {
        this.listener = listener;
    }

    public DiscountSelectView(Context context) {
        this(context, null);
    }

    public DiscountSelectView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DiscountSelectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    private void initView() {
        View.inflate(context, R.layout.view_discount_layout, this);
        ButterKnife.bind(this);

        tv00.setOnClickListener(this);
        tv50.setOnClickListener(this);
        tv60.setOnClickListener(this);
        tv70.setOnClickListener(this);
        tv75.setOnClickListener(this);
        tv80.setOnClickListener(this);
        tv85.setOnClickListener(this);
        tv88.setOnClickListener(this);
        tv90.setOnClickListener(this);
        tv95.setOnClickListener(this);
        tvZero.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String discount ;
        switch (v.getId()) {
            case R.id.tv_00:
            case R.id.tv_50:
            case R.id.tv_60:
            case R.id.tv_70:
            case R.id.tv_75:
            case R.id.tv_80:
            case R.id.tv_85:
            case R.id.tv_88:
            case R.id.tv_90:
            case R.id.tv_95:
                TextView tv = ((TextView)v);
                discount =tv.getText().toString();
                if (listener != null){
                    listener.clickListener(discount, Constant.VIEW_CLICK_TYPE_NUMBER);
                }
                break;
            default:
                discount ="-1";
                if (listener != null){
                    listener.clickListener(discount, Constant.VIEW_CLICK_TYPE_COMFIRM);
                }
        }

    }
}
