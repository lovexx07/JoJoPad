package com.jojo.pad.ui.activity.account;

import android.view.View;
import android.widget.LinearLayout;

import com.jojo.pad.R;
import com.jojo.pad.base.BaseAcitivty;

import butterknife.BindView;

/**
 * 选择业务类型
 */
public class CompanyStyleActivity extends BaseAcitivty {
    @BindView(R.id.ll)
    LinearLayout ll;
    @Override
    public void dataBinding() {
        setContentView(R.layout.activity_company_style);
    }

    @Override
    public void initView() {

    }

    @Override
    public void setListener() {
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toActivity(EmployeeLoginActivity.class);
            }
        });
    }

    @Override
    public void initData() {

    }
}
