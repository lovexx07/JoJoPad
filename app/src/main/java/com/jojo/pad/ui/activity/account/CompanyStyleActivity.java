package com.jojo.pad.ui.activity.account;

import android.view.View;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.jojo.pad.R;
import com.jojo.pad.base.BaseAcitivty;

import butterknife.BindView;

/**
 * 选择业务类型
 */
public class CompanyStyleActivity extends BaseAcitivty {
    @BindView(R.id.ll_1)
    LinearLayout ll1;
    @BindView(R.id.ll_2)
    LinearLayout ll2;
    @BindView(R.id.ll_3)
    LinearLayout ll3;

    @Override
    public int getLayoutId() {
        return R.layout.activity_company_style;
    }

    @Override
    public void initView() {

    }

    @Override
    public void setListener() {
        ll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showShort("你选择了");
                toActivity(EmployeeLoginActivity.class);
            }
        });
    }

    @Override
    public void initData() {

    }
}
