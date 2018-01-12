package com.jojo.pad.ui.activity.member;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jojo.pad.R;
import com.jojo.pad.base.BaseAcitivty;
import com.jojo.pad.constant.Constant;
import com.jojo.pad.constant.HttpConstant;
import com.jojo.pad.listener.ResponseListener;
import com.jojo.pad.listener.ViewClickListener;
import com.jojo.pad.model.bean.result.MemberListBean;
import com.jojo.pad.model.http.BaseHttp;
import com.jojo.pad.util.AccountUtil;
import com.jojo.pad.util.Convert;
import com.jojo.pad.widget.NumberSelectView;
import com.jojo.pad.widget.PadHeader;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MemberRechargeActivity extends BaseAcitivty implements View.OnClickListener {
    @BindView(R.id.pheader)
    PadHeader pheader;
    @BindView(R.id.tv_recharge)
    TextView tvRecharge;
    @BindView(R.id.tv_send)
    TextView tvSend;
    @BindView(R.id.gadiogroup)
    RadioGroup gadiogroup;
    @BindView(R.id.cb_print)
    CheckBox cbPrint;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    private PopupWindow mPopWindow;
    private NumberSelectView numberSelect;
    private boolean iseditrecharge = true;

    private String cid;
    //1现金 2储值卡 3微信 4支付宝
    private int rechargeType = 1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_member_recharge;
    }

    @Override
    public void initView() {
        cid = getIntent().getStringExtra("cid");
    }

    @Override
    public void setListener() {
        tvRecharge.setOnClickListener(this);
        tvSend.setOnClickListener(this);
        tvConfirm.setOnClickListener(this);
        gadiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int radioid) {
                switch (radioid) {
                    case R.id.tv_cash:
                        rechargeType = 1;
                        break;
                    case R.id.tv_card:
                        rechargeType = 2;
                        break;
                    case R.id.tv_weixin:
                        rechargeType = 3;
                        break;
                    case R.id.tv_zhifubao:
                        rechargeType = 4;
                        break;
                    default:
                        rechargeType = 1;
                        break;
                }
            }
        });
    }

    @Override
    public void initData() {
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_recharge:
                iseditrecharge = true;
                showPopWindow(view);
                break;
            case R.id.tv_send:
                iseditrecharge = false;
                showPopWindow(view);
                break;
            case R.id.tv_confirm:
                recharge();
                break;
            default:
                break;
        }
    }

    private void showPopWindow(View view) {
        int x = 0;
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.pop_number_layout, null);
        mPopWindow = new PopupWindow(contentView);

        mPopWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopWindow.setOutsideTouchable(true);
        numberSelect = contentView.findViewById(R.id.number_select);
        numberSelect.setViewClickListener(new ViewClickListener() {
            @Override
            public void clickListener(String msg, int type) {
                if (type == Constant.VIEW_CLICK_TYPE_NUMBER) {
                    if (iseditrecharge) {
                        tvRecharge.setText(msg);
                    } else {
                        tvSend.setText(msg);
                    }
                } else {
                    mPopWindow.dismiss();
                }
            }
        });
        x = SizeUtils.getMeasuredWidth(contentView) / 2;
        mPopWindow.showAsDropDown(view, -x, 0);
    }

    private void recharge() {
        float edit_fee = Float.parseFloat(tvRecharge.getText().toString()) + Float.parseFloat(tvSend.getText().toString());
        Map<String, String> map = new HashMap<>();
        map.put("store_id", AccountUtil.store_id);
        map.put("user_id", AccountUtil.user_id);
        map.put("cid", cid);
        map.put("change_fee", edit_fee + "");
        map.put("edit_fee", tvRecharge.getText().toString());
        map.put("pay_type", rechargeType + "");
        BaseHttp.postJson(HttpConstant.Api.rechargeAdd, map, activity, new ResponseListener() {
            @Override
            public void onSuccess(Object data) {
                ToastUtils.showShort("充值成功");
                setResult(RESULT_OK);
                finish();
            }
            @Override
            public void onError(String result) {
                ToastUtils.showShort(result);
            }
        });
    }
}
