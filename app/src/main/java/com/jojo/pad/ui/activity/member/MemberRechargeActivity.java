package com.jojo.pad.ui.activity.member;

import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jojo.pad.R;
import com.jojo.pad.base.BaseAcitivty;
import com.jojo.pad.constant.Constant;
import com.jojo.pad.constant.HttpConstant;
import com.jojo.pad.listener.ResponseListener;
import com.jojo.pad.listener.ViewClickListener;
import com.jojo.pad.model.bean.print.PrintRecharge;
import com.jojo.pad.model.http.BaseHttp;
import com.jojo.pad.print.UsbPrinter;
import com.jojo.pad.util.AccountUtil;
import com.jojo.pad.util.PrinterUtil;
import com.jojo.pad.util.ThreadPoolManager;
import com.jojo.pad.widget.NumberSelectView;
import com.jojo.pad.widget.PadHeader;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class MemberRechargeActivity extends BaseAcitivty implements View.OnClickListener {
    @BindView(R.id.pheader)
    PadHeader pheader;
    @BindView(R.id.tv_recharge)
    TextView tvRecharge;
    @BindView(R.id.tv_send)
    TextView tvSend;
    @BindView(R.id.gadiogroup)
    RadioGroup gadiogroup;
    @BindView(R.id.cb_print)
    CheckBox cbPrint;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    private PopupWindow mPopWindow;
    private NumberSelectView numberSelect;
    private boolean iseditrecharge = true;

    private String cid,recharge_fee,number;//id 余额  卡号
    //1现金 2储值卡 3微信 4支付宝
    private int rechargeType = 1;
    private String paytype="现金";

    private UsbPrinter usbprint;
    private float edit_fee;//充值金额

    @Override
    public int getLayoutId() {
        return R.layout.activity_member_recharge;
    }

    @Override
    public void initView() {
        cid = getIntent().getStringExtra("cid");
        recharge_fee = getIntent().getStringExtra("recharge_fee");
        number = getIntent().getStringExtra("number");
        usbprint = UsbPrinter.getInstance();
        usbprint.init(this);
    }

    @Override
    public void setListener() {
        tvRecharge.setOnClickListener(this);
        tvSend.setOnClickListener(this);
        tvConfirm.setOnClickListener(this);
        pheader.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        gadiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int radioid) {
                switch (radioid) {
                    case R.id.tv_cash:
                        rechargeType = 1;
                        paytype = "现金";
                        break;
                    case R.id.tv_card:
                        rechargeType = 2;
                        paytype = "储值卡";
                        break;
                    case R.id.tv_weixin:
                        rechargeType = 3;
                        paytype = "微信";
                        break;
                    case R.id.tv_zhifubao:
                        rechargeType = 4;
                        paytype = "支付宝";
                        break;
                    default:
                        rechargeType = 1;
                        paytype = "现金";
                        break;
                }
            }
        });
    }

    @Override
    public void initData() {
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_recharge:
                iseditrecharge = true;
                showPopWindow(view);
                break;
            case R.id.tv_send:
                iseditrecharge = false;
                showPopWindow(view);
                break;
            case R.id.tv_confirm:
                recharge();
                break;
            default:
                break;
        }
    }

    private void showPopWindow(View view) {
        int x = 0;
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.pop_number_layout, null);
        mPopWindow = new PopupWindow(contentView);

        mPopWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopWindow.setOutsideTouchable(true);
        numberSelect = contentView.findViewById(R.id.number_select);
        numberSelect.setViewClickListener(new ViewClickListener() {
            @Override
            public void clickListener(String msg, int type) {
                if (type == Constant.VIEW_CLICK_TYPE_NUMBER) {
                    if (iseditrecharge) {
                        tvRecharge.setText(msg);
                    } else {
                        tvSend.setText(msg);
                    }
                } else {
                    mPopWindow.dismiss();
                }
            }
        });
        x = SizeUtils.getMeasuredWidth(contentView) / 2;
        mPopWindow.showAsDropDown(view, -x, 0);
    }

    private void recharge() {
         edit_fee = Float.parseFloat(tvRecharge.getText().toString()) + Float.parseFloat(tvSend.getText().toString());
        Map<String, String> map = new HashMap<>();
        map.put("store_id", AccountUtil.store_id);
        map.put("user_id", AccountUtil.user_id);
        map.put("cid", cid);
        map.put("change_fee", edit_fee + "");
        map.put("edit_fee", tvRecharge.getText().toString());
        map.put("pay_type", rechargeType + "");
        BaseHttp.postJson(HttpConstant.Api.rechargeAdd, map, activity, new ResponseListener() {
            @Override
            public void onSuccess(Object data) {
                ToastUtils.showShort("充值成功");
                if (cbPrint.isChecked()){
                    printDocuments();
                }else {
                    setResult(RESULT_OK);
                    finish();
                }
            }
            @Override
            public void onError(String result) {
                ToastUtils.showShort(result);
            }
        });
    }

    private void printDocuments() {
        final PrintRecharge printRecharge = new PrintRecharge();
        printRecharge.setNumber(number);
        printRecharge.setPaytype(paytype);
        printRecharge.setRecharge_fee(recharge_fee);
        printRecharge.setRecharge(edit_fee+"");
        printRecharge.setSend(tvSend.getText().toString());
        float recharge_end=0;
        try {
             recharge_end = Float.parseFloat(recharge_fee) + edit_fee;
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        printRecharge.setRecharge_end(recharge_end+"");
        ThreadPoolManager.newInstance().addExecuteTask(new Runnable() {
            @Override
            public void run() {
                try {
                            usbprint.sendMessageToPoint(PrinterUtil.printRecharge(printRecharge));
                } catch (Exception e) {
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setResult(RESULT_OK);
                        finish();
                    }
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        usbprint.onDestory();
    }
}
