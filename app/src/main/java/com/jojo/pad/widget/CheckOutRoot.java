package com.jojo.pad.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.jojo.pad.R;
import com.jojo.pad.constant.Constant;
import com.jojo.pad.listener.ViewClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ruifeng on 2018/1/10.
 */

public class CheckOutRoot extends LinearLayout implements View.OnClickListener {
    @BindView(R.id.btn_1)
    Button btn1;
    @BindView(R.id.btn_2)
    Button btn2;
    @BindView(R.id.btn_3)
    Button btn3;
    @BindView(R.id.btn_50)
    Button btn50;
    @BindView(R.id.btn_4)
    Button btn4;
    @BindView(R.id.btn_5)
    Button btn5;
    @BindView(R.id.btn_6)
    Button btn6;
    @BindView(R.id.btn_100)
    Button btn100;
    @BindView(R.id.btn_7)
    Button btn7;
    @BindView(R.id.btn_8)
    Button btn8;
    @BindView(R.id.btn_9)
    Button btn9;
    @BindView(R.id.btn_200)
    Button btn200;
    @BindView(R.id.btn_d_0)
    Button btnD0;
    @BindView(R.id.btn_0)
    Button btn0;
    @BindView(R.id.btn_t)
    Button btnT;
    @BindView(R.id.btn_detail)
    Button btnDetail;
    @BindView(R.id.tv_confirm)
    Button tvConfirm;
    @BindView(R.id.btn_d)
    ImageButton btnD;

    private Context context;
    private ViewClickListener listener;
    private StringBuilder stringBuilder;

    public CheckOutRoot(Context context) {
        this(context, null);
    }

    public CheckOutRoot(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CheckOutRoot(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    public void setViewClickListener(ViewClickListener listener){
        this.listener = listener;
    }

    private void initView() {
        View.inflate(context, R.layout.check_out_root_layout, this);
        ButterKnife.bind(this);
        stringBuilder = new StringBuilder();

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnT.setOnClickListener(this);
        btnD.setOnClickListener(this);
        btnD0.setOnClickListener(this);
        btn50.setOnClickListener(this);
        btn100.setOnClickListener(this);
        btn200.setOnClickListener(this);
        btnDetail.setOnClickListener(this);
        tvConfirm.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_0:
                if ("0".equals(stringBuilder.toString().trim()) ||"00".equals(stringBuilder.toString().trim())) {
                    break;
                }
                stringBuilder.append(0);
                setNumber();
                break;
            case R.id.btn_1:
                stringBuilder.append(1);
                setNumber();
                break;
            case R.id.btn_2:
                stringBuilder.append(2);
                setNumber();
                break;
            case R.id.btn_3:
                stringBuilder.append(3);
                setNumber();
                break;
            case R.id.btn_4:
                stringBuilder.append(4);
                setNumber();
                break;
            case R.id.btn_5:
                stringBuilder.append(5);
                setNumber();
                break;
            case R.id.btn_6:
                stringBuilder.append(6);
                setNumber();
                break;
            case R.id.btn_7:
                stringBuilder.append(7);
                setNumber();
                break;
            case R.id.btn_8:
                stringBuilder.append(8);
                setNumber();
                break;
            case R.id.btn_9:
                stringBuilder.append(9);
                setNumber();
                break;
            case R.id.btn_t:
                if (stringBuilder.indexOf(".") == -1) {
                    if (TextUtils.isEmpty(stringBuilder.toString().trim())) {
                        stringBuilder.append("0");
                    }
                    stringBuilder.append(".");
                    setNumber();
                }
                break;
            case R.id.btn_d:
                if (stringBuilder.length() > 0) {
                    stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                }
                setNumber();
                break;
            case R.id.btn_d_0:
                if ("0".equals(stringBuilder.toString().trim()) ||"00".equals(stringBuilder.toString().trim())) {
                    break;
                }
                stringBuilder.append("00");
                setNumber();
                break;
            case R.id.btn_50:
                stringBuilder.append(50);
                setNumber();
                break;
            case R.id.btn_100:
                stringBuilder.append(100);
                setNumber();
                break;
            case R.id.btn_200:
                stringBuilder.append(200);
                setNumber();
                break;
            case R.id.btn_detail:

                break;
            case R.id.bt_confirm:
                setConfirm();
                break;
            default:
        }
    }
    private void setNumber() {
        if (listener != null){
            listener.clickListener(stringBuilder.toString(), Constant.VIEW_CLICK_TYPE_NUMBER);
        }
    }
    private void setConfirm() {
        if (listener != null){
            listener.clickListener(stringBuilder.toString(), Constant.VIEW_CLICK_TYPE_NUMBER);
        }
    }
}
