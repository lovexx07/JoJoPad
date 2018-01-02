package com.jojo.pad.ui.activity.account;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jojo.pad.R;
import com.jojo.pad.base.BaseAcitivty;
import com.jojo.pad.ui.activity.MainActivity;
import com.jojo.pad.ui.activity.companystyle.NormalCompanyActivity;

import butterknife.BindView;

/**
 * 员工登陆
 */
public class EmployeeLoginActivity extends BaseAcitivty {


    @BindView(R.id.et_num)
    EditText etNum;
    @BindView(R.id.et_paw)
    EditText etPaw;
    @BindView(R.id.bt_confirm)
    TextView btConfirm;

    @Override
    public int getLayoutId() {
        return R.layout.activity_employee_login;
    }

    @Override
    public void initView() {

    }

    @Override
    public void setListener() {
        btConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num = etNum.getText().toString();
                String pwd = etPaw.getText().toString();

                if ("1001".equals(num) && "1001".equals(pwd)){
                    toActivity(NormalCompanyActivity.class);
                }
            }
        });
    }

    @Override
    public void initData() {

    }

}
