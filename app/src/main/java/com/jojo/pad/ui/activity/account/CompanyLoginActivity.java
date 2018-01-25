package com.jojo.pad.ui.activity.account;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jojo.pad.R;
import com.jojo.pad.base.BaseAcitivty;
import com.jojo.pad.constant.HttpConstant;
import com.jojo.pad.listener.ResponseListener;
import com.jojo.pad.model.bean.result.UserSignInBean;
import com.jojo.pad.model.http.BaseHttp;
import com.jojo.pad.ui.activity.companystyle.NormalCompanyActivity;
import com.jojo.pad.util.AccountUtil;
import com.jojo.pad.util.Convert;
import com.jojo.pad.widget.CircleImageView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

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
//        header.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                toActivity(SerialPortActivity.class);
//            }
//        });
//        header.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                toActivity(PrintDemoActivity.class);
//                return true;
//            }
//        });
    }

    @Override
    public void initData() {
        btConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = etAccount.getText().toString();
                String password = etPaw.getText().toString();
                Map<String, String> map = new HashMap<>();
                map.put("phone", account);
                map.put("pwd", EncryptUtils.encryptMD5ToString(password));
                BaseHttp.postJson(HttpConstant.Api.signIn, map, activity, new ResponseListener() {
                    @Override
                    public void onSuccess(Object result) {
                        UserSignInBean data = Convert.fromJObject(result,UserSignInBean.class);
                        AccountUtil.user_id = data.getUser_id();
                        AccountUtil.operator = data.getOperator();
                        AccountUtil.store_name = data.getStore_name();
                        AccountUtil.store_id = data.getStore_id();
                        toActivity(NormalCompanyActivity.class);
                        finish();
                    }
                    @Override
                    public void onError(String result) {
                        ToastUtils.showShort(result);
                    }
                });

            }
        });
    }
}
