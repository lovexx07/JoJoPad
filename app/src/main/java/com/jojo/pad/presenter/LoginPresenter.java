package com.jojo.pad.presenter;


import com.jojo.pad.base.BaseAcitivty;
import com.jojo.pad.model.bean.CompanyBean;
import com.jojo.pad.model.bean.EmployeeBean;
import com.jojo.pad.ui.activity.account.CompanyStyleActivity;
import com.jojo.pad.util.ToastUtil;

/**
 * Created by Administrator on 2017/12/28 0028.
 */

public class LoginPresenter {
    private BaseAcitivty acitivty;

    public LoginPresenter(BaseAcitivty acitivty) {
        this.acitivty = acitivty;
    }

    public void companyLogin(CompanyBean bean) {
        ToastUtil.show(bean.getCompanyaccount());

        if (acitivty != null){
            acitivty.toActivity(CompanyStyleActivity.class);
        }
    }
    public void employeeLogin(EmployeeBean bean) {
        ToastUtil.show(bean.getAccount());

        if (acitivty != null){
            acitivty.toActivity(CompanyStyleActivity.class);
        }
    }
}
