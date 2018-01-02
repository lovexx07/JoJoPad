package com.jojo.pad.ui.activity.account;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jojo.pad.R;
import com.jojo.pad.base.BaseAcitivty;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 公司账号登陆
 */
public class CompanyLoginActivity extends BaseAcitivty {

    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_paw)
    EditText etPaw;
    @BindView(R.id.bt_confirm)
    TextView btConfirm;

    @Override
    public int getLayoutId() {
        return R.layout.activity_company_login;
    }

    @Override
    public void initView() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void initData() {
        btConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = etAccount.getText().toString();
                String password = etPaw.getText().toString();

                if ("jojo2904".equals(account) && "123456".equals(password)){
                    toActivity(CompanyStyleActivity.class);
                }
            }
        });
    }
}
