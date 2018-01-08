package com.jojo.pad.ui.activity.account;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.EncryptUtils;
import com.jojo.pad.R;
import com.jojo.pad.base.BaseAcitivty;
import com.jojo.pad.model.bean.CompanyBean;
import com.jojo.pad.print.PrintDemoActivity;
import com.jojo.pad.util.UsbDemoActivity;
import com.jojo.pad.widget.CircleImageView;

import butterknife.BindView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                toActivity(UsbDemoActivity.class);
            }
        });
        header.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                toActivity(PrintDemoActivity.class);
                return true;
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

                    CompanyBean bean = new CompanyBean();
                    bean.setPhone("12222222222");
                    bean.setPwd(EncryptUtils.encryptMD5ToString("111111"));
                    RetrofitManager.getInstance().server.slogin(bean).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });
                }
            }
        });
    }

}
