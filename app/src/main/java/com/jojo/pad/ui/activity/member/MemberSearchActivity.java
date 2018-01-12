package com.jojo.pad.ui.activity.member;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jojo.pad.R;
import com.jojo.pad.adapter.MemberAdapter;
import com.jojo.pad.base.BaseAcitivty;
import com.jojo.pad.constant.Constant;
import com.jojo.pad.constant.HttpConstant;
import com.jojo.pad.listener.ResponseListener;
import com.jojo.pad.listener.ViewClickListener;
import com.jojo.pad.model.bean.result.MemberListBean;
import com.jojo.pad.model.http.BaseHttp;
import com.jojo.pad.util.AccountUtil;
import com.jojo.pad.util.Convert;
import com.jojo.pad.widget.CheckOutRoot;
import com.jojo.pad.widget.SearchView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 会员
 */
public class MemberSearchActivity extends BaseAcitivty implements View.OnClickListener, ViewClickListener {
    @BindView(R.id.iv_help)
    TextView ivHelp;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.searchview)
    SearchView searchview;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.checkoutroot)
    CheckOutRoot checkoutroot;

    private MemberAdapter memberAdapter;
    private List<MemberListBean.MemberBean> datas;

    @Override
    public int getLayoutId() {
        return R.layout.activity_member_search;
    }

    @Override
    public void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(linearLayoutManager);
        datas = new ArrayList<>();
        memberAdapter = new MemberAdapter(datas);
        recyclerview.setAdapter(memberAdapter);
    }

    @Override
    public void setListener() {
        searchview.setSearchListener(this);
        checkoutroot.setSearchListener(this);
        ivHelp.setOnClickListener(this);
        tvBack.setOnClickListener(this);
        memberAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MemberListBean.MemberBean bean = datas.get(position);
                Bundle intent = new Bundle();
                intent.putString("cid", bean.getCid());
                intent.putString("cname", bean.getName());
                intent.putString("crecharge", bean.getRecharge_fee());
                toActivity(MemberDetailActivity.class, intent);
                finish();
            }
        });
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

    @Override
    public void clickListener(String msg, int type) {
        if (type == Constant.VIEW_CLICK_TYPE_SEARCH || type == Constant.VIEW_CLICK_TYPE_COMFIRM) {
            if (!TextUtils.isEmpty(searchview.getSearchString())) {
                searchMember(searchview.getSearchString());
            }
        } else if (type == Constant.VIEW_CLICK_TYPE_NUMBER) {
            searchview.setSearchString(msg);
        }
    }

    private void searchMember(String msg) {
        Map<String, String> map = new HashMap<>();
        map.put("store_id", AccountUtil.store_id);
        map.put("user_id", AccountUtil.user_id);
        map.put("keyword", msg);
        BaseHttp.getJson(HttpConstant.Api.searchMember, map, activity, new ResponseListener() {
            @Override
            public void onSuccess(Object data) {
                MemberListBean memberListBean = Convert.fromJObject(data, MemberListBean.class);
                memberAdapter.addData(memberListBean.getCustomer_list());
            }
            @Override
            public void onError(String result) {
                ToastUtils.showShort(result);
            }
        });
    }
}
