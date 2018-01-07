package com.jojo.pad.ui.activity.account;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jojo.pad.ComAssistantActivity;
import com.jojo.pad.R;
import com.jojo.pad.base.BaseAcitivty;
import com.jojo.pad.ui.activity.companystyle.NormalCompanyActivity;
import com.jojo.pad.util.MainActivity;
import com.jojo.pad.uu.MainActivity2;
import com.jojo.pad.widget.CircleImageView;

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
    @BindView(R.id.header)
    CircleImageView header;

    @Override
    public int getLayoutId() {
        return R.layout.activity_company_login;
    }

    @Override
    public void initView() {

    }

    @Override
    public void setListener() {
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toActivity(MainActivity2.class);
            }
        });
    }

    @Override
    public void initData() {
        btConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = etAccount.getText().toString();
                String password = etPaw.getText().toString();

                if ("jojo2904".equals(account) && "123456".equals(password)) {
                    toActivity(NormalCompanyActivity.class);
                }
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
