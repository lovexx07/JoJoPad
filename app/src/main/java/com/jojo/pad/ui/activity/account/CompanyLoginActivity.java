package com.jojo.pad.ui.activity.account;

import android.databinding.DataBindingUtil;

import com.jojo.pad.R;
import com.jojo.pad.base.BaseAcitivty;
import com.jojo.pad.databinding.ActivityCompanyLoginBinding;
import com.jojo.pad.model.bean.CompanyBean;
import com.jojo.pad.presenter.LoginPresenter;

/**
 * 公司账号登陆
 */
public class CompanyLoginActivity extends BaseAcitivty {
    private ActivityCompanyLoginBinding binding;
    private LoginPresenter presenter;

    @Override
    public void dataBinding() {
        presenter = new LoginPresenter(this);
        CompanyBean bean = new CompanyBean();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_company_login);
        binding.setUser(bean);
        binding.setPresenter(presenter);
    }

    @Override
    public void initView() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void initData() {

    }
}
