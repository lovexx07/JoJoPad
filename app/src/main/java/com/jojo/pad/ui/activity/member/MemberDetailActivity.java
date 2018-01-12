package com.jojo.pad.ui.activity.member;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.jojo.pad.R;
import com.jojo.pad.base.BaseAcitivty;
import com.jojo.pad.constant.Constant;
import com.jojo.pad.constant.HttpConstant;
import com.jojo.pad.evenbean.MemberEvenBean;
import com.jojo.pad.listener.ResponseListener;
import com.jojo.pad.model.bean.result.MemberDetail;
import com.jojo.pad.model.bean.result.MemberRecharge;
import com.jojo.pad.model.http.BaseHttp;
import com.jojo.pad.util.AccountUtil;
import com.jojo.pad.util.Convert;
import com.jojo.pad.widget.EditTextItem;
import com.jojo.pad.widget.PadHeader;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MemberDetailActivity extends BaseAcitivty implements View.OnClickListener {

    @BindView(R.id.eti_ids)
    EditTextItem etiIds;
    @BindView(R.id.eti_name)
    EditTextItem etiName;
    @BindView(R.id.eti_phone)
    EditTextItem etiPhone;
    @BindView(R.id.tv_recharge)
    TextView tvRecharge;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.eti_birth)
    EditTextItem etiBirth;
    @BindView(R.id.eti_address)
    EditTextItem etiAddress;
    @BindView(R.id.eti_qq)
    EditTextItem etiQq;
    @BindView(R.id.eti_email)
    EditTextItem etiEmail;
    @BindView(R.id.eti_like)
    EditTextItem etiLike;
    @BindView(R.id.eti_detail)
    EditTextItem etiDetail;
    @BindView(R.id.eti_weixin)
    EditTextItem etiWeixin;
    @BindView(R.id.tv_cancle)
    TextView tvCancle;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    @BindView(R.id.header)
    PadHeader pheader;
    private String cid, cname, crecharge, number;
    private boolean canchose;

    @Override
    public int getLayoutId() {
        return R.layout.activity_member_detail;
    }

    @Override
    public void initView() {
        cid = getIntent().getStringExtra("cid");
        cname = getIntent().getStringExtra("cname");
        crecharge = getIntent().getStringExtra("crecharge");
        number = getIntent().getStringExtra("number");
        etiIds.setEditTextValue(number);

        if (getIntent().hasExtra("type")) {
            canchose = true;
            tvConfirm.setText("取消选择");
        }
    }

    @Override
    public void setListener() {
        tvCancle.setOnClickListener(this);
        tvConfirm.setOnClickListener(this);
        pheader.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void initData() {
        getMemberDetail();
        getRecharge();
    }

    private void getRecharge() {
        final Map<String, String> map = new HashMap<>();
        map.put("store_id", AccountUtil.store_id);
        map.put("user_id", AccountUtil.user_id);
        map.put("cid", cid);
        BaseHttp.getJson(HttpConstant.Api.memberGetRecharge, map, activity, new ResponseListener() {
            @Override
            public void onSuccess(Object result) {
                MemberRecharge memberRecharge = Convert.fromJObject(result, MemberRecharge.class);
                tvRecharge.setText(memberRecharge.getRecharge_fee());
                tvTotal.setText(memberRecharge.getTotal_cost());
            }

            @Override
            public void onError(String result) {
                ToastUtils.showShort(result);
            }
        });
    }

    private void getMemberDetail() {
        final Map<String, String> map = new HashMap<>();
        map.put("store_id", AccountUtil.store_id);
        map.put("user_id", AccountUtil.user_id);
        map.put("cid", cid);
        BaseHttp.getJson(HttpConstant.Api.memberGetInfo, map, activity, new ResponseListener() {
            @Override
            public void onSuccess(Object result) {
                MemberDetail memberNumber = Convert.fromJObject(result, MemberDetail.class);

                etiName.setEditTextValue(memberNumber.getName());
                etiPhone.setEditTextValue(memberNumber.getPhone());
                etiAddress.setEditTextValue(memberNumber.getAddress());
                etiDetail.setEditTextValue(memberNumber.getDesc());
                etiLike.setEditTextValue(memberNumber.getLike());
                etiEmail.setEditTextValue(memberNumber.getEmail());
                etiQq.setEditTextValue(memberNumber.getQq());
                etiWeixin.setEditTextValue(memberNumber.getWeixin());

                for (MemberDetail.AnniversaryBean bean : memberNumber.getAnniversary()) {
                    if ("会员生日".equals(bean.getDate_name()) && !TextUtils.isEmpty(bean.getDate_time())) {
                        etiBirth.setEditTextValue(bean.getDate_time());
                    }
                }

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancle:
                Bundle bundle = new Bundle();
                bundle.putString("cid", cid);
                bundle.putString("recharge_fee", tvRecharge.getText().toString());
                bundle.putString("number", number);
                toActivityForResult(MemberRechargeActivity.class, bundle, Constant.INTENT_FAR_RESULT_A);
                break;
            case R.id.tv_confirm:
                if (canchose) {
                    EventBus.getDefault().postSticky(new MemberEvenBean(cid, cname, crecharge, true));
                } else {
                    EventBus.getDefault().postSticky(new MemberEvenBean(cid, cname, crecharge, false));
                }

                finish();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.INTENT_FAR_RESULT_A && resultCode == RESULT_OK) {
            initData();
        }
    }
}
