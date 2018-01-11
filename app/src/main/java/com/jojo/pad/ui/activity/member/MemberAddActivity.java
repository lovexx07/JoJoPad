package com.jojo.pad.ui.activity.member;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.jojo.pad.R;
import com.jojo.pad.base.BaseAcitivty;
import com.jojo.pad.constant.HttpConstant;
import com.jojo.pad.listener.ResponseListener;
import com.jojo.pad.model.bean.result.MemberNumber;
import com.jojo.pad.model.http.BaseHttp;
import com.jojo.pad.util.AccountUtil;
import com.jojo.pad.util.Convert;
import com.jojo.pad.widget.EditTextItem;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MemberAddActivity extends BaseAcitivty implements View.OnClickListener {
    @BindView(R.id.eti_ids)
    EditTextItem etiIds;
    @BindView(R.id.eti_name)
    EditTextItem etiName;
    @BindView(R.id.eti_phone)
    EditTextItem etiPhone;
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
    @BindView(R.id.tv_cancle)
    TextView tvCancle;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    @BindView(R.id.eti_weixin)
    EditTextItem etiWeixin;

    private String number = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_member_add;
    }

    @Override
    public void initView() {

    }

    @Override
    public void setListener() {
        tvConfirm.setOnClickListener(this);
        tvCancle.setOnClickListener(this);
    }

    @Override
    public void initData() {
        getMemberNumber();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_confirm:
                addMember();
                break;
            case R.id.tv_cancle:
                finish();
                break;
            default:
                break;
        }
    }

    private void addMember() {
        String datestr = etiBirth.getEditTextValue();

        Map<String, String> map = new HashMap<>();
        map.put("store_id", AccountUtil.store_id);
        map.put("user_id", AccountUtil.user_id);
        map.put("number", number);
        map.put("name", etiName.getEditTextValue());
        map.put("phone", etiPhone.getEditTextValue());
        map.put("qq", etiQq.getEditTextValue());
        map.put("email", etiEmail.getEditTextValue());
        map.put("address", etiAddress.getEditTextValue());
        map.put("like", etiLike.getEditTextValue());
        map.put("weixin", etiWeixin.getEditTextValue());
        map.put("desc", etiDetail.getEditTextValue());
        if (!TextUtils.isEmpty(datestr)) {
            long aaa = TimeUtils.string2Millis(datestr, new SimpleDateFormat("yyyy-MM-dd HH:mm"));
            List<Map<String,String>> dates = new ArrayList<>();
            Map<String,String> date = new HashMap<>();
            date.put("date_name","会员生日");
            date.put("date_time",aaa+"");
            dates.add(date);
            map.put("anniversary", Convert.toJson(dates));

        }

        BaseHttp.postJson(HttpConstant.Api.addMember, map, activity, new ResponseListener() {
            @Override
            public void onSuccess(Object result) {
                finish();
            }
        });
    }

    private void getMemberNumber() {
        Map<String, String> map = new HashMap<>();
        map.put("store_id", AccountUtil.store_id);
        map.put("user_id", AccountUtil.user_id);
        BaseHttp.getJson(HttpConstant.Api.getMemberNember, map, activity, new ResponseListener() {
            @Override
            public void onSuccess(Object result) {
                MemberNumber memberNumber = Convert.fromJObject(result, MemberNumber.class);
                number = memberNumber.getNumber();
                etiIds.setEditTextValue(number);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
