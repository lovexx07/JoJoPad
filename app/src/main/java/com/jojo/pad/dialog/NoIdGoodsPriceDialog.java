package com.jojo.pad.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jojo.pad.R;
import com.jojo.pad.constant.Constant;
import com.jojo.pad.listener.ViewClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/1/2 0002.
 */

public class NoIdGoodsPriceDialog extends Dialog {
    public NoIdGoodsPriceDialog(@NonNull Context context) {
        super(context);
    }

    public NoIdGoodsPriceDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected NoIdGoodsPriceDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder implements View.OnClickListener {
        @BindView(R.id.iv_close)
        ImageView ivClose;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.btn_1)
        Button btn1;
        @BindView(R.id.btn_2)
        Button btn2;
        @BindView(R.id.btn_3)
        Button btn3;
        @BindView(R.id.btn_4)
        Button btn4;
        @BindView(R.id.btn_5)
        Button btn5;
        @BindView(R.id.btn_6)
        Button btn6;
        @BindView(R.id.btn_7)
        Button btn7;
        @BindView(R.id.btn_8)
        Button btn8;
        @BindView(R.id.btn_9)
        Button btn9;
        @BindView(R.id.btn_t)
        Button btnT;
        @BindView(R.id.btn_0)
        Button btn0;
        @BindView(R.id.btn_d)
        ImageButton btnD;
        @BindView(R.id.tv_confirm)
        Button tvConfirm;


        private Context context;
        private ViewClickListener listener;

        private StringBuilder stringBuilder;
        private NoIdGoodsPriceDialog dialog;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setListener(ViewClickListener listener) {
            this.listener = listener;
            return this;
        }

        public NoIdGoodsPriceDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            dialog = new NoIdGoodsPriceDialog(context, R.style.Dialog);
            View layout = inflater.inflate(R.layout.dialog_price_layout, null);
            dialog.addContentView(layout,
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            ButterKnife.bind(this, dialog);
            stringBuilder = new StringBuilder();

            ivClose.setOnClickListener(this);
            btn0.setOnClickListener(this);
            btn1.setOnClickListener(this);
            btn2.setOnClickListener(this);
            btn3.setOnClickListener(this);
            btn4.setOnClickListener(this);
            btn5.setOnClickListener(this);
            btn6.setOnClickListener(this);
            btn7.setOnClickListener(this);
            btn8.setOnClickListener(this);
            btn9.setOnClickListener(this);
            btnT.setOnClickListener(this);
            btnD.setOnClickListener(this);
            tvConfirm.setOnClickListener(this);

            dialog.setContentView(layout);
            return dialog;
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.iv_close:
                    stringBuilder.delete(0, stringBuilder.length());
                    dialog.dismiss();
                    return;
                case R.id.btn_t:
                    if (stringBuilder.indexOf(".") == -1) {
                        if (TextUtils.isEmpty(stringBuilder.toString().trim())) {
                            stringBuilder.append("0");
                        }
                        stringBuilder.append(".");
                        setNumber();
                    }
                    break;
                case R.id.btn_0:
                    if ("0".equals(stringBuilder.toString().trim())) {
                        break;
                    }
                    stringBuilder.append(0);
                    setNumber();
                    break;
                case R.id.btn_1:
                    stringBuilder.append(1);
                    setNumber();
                    break;
                case R.id.btn_2:
                    stringBuilder.append(2);
                    setNumber();
                    break;
                case R.id.btn_3:
                    stringBuilder.append(3);
                    setNumber();
                    break;
                case R.id.btn_4:
                    stringBuilder.append(4);
                    setNumber();
                    break;
                case R.id.btn_5:
                    stringBuilder.append(5);
                    setNumber();
                    break;
                case R.id.btn_6:
                    stringBuilder.append(6);
                    setNumber();
                    break;
                case R.id.btn_7:
                    stringBuilder.append(7);
                    setNumber();
                    break;
                case R.id.btn_8:
                    stringBuilder.append(8);
                    setNumber();
                    break;
                case R.id.btn_9:
                    stringBuilder.append(9);
                    setNumber();
                    break;
                case R.id.tv_confirm:
                    if (listener != null){
                        double price = Double.parseDouble(tvPrice.getText().toString());
                        if (price ==0){
                            ToastUtils.showShort("无码商品售价不能为0");
                            return;
                        }
                        listener.clickListener(tvPrice.getText().toString(), Constant.VIEW_CLICK_TYPE_NOID);
                    }
                    dialog.dismiss();
                    break;
                case R.id.btn_d:
                    if (stringBuilder.length() > 0) {
                        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                    }
                    setNumber();
                    break;
                default:
                    break;
            }
        }

        private void setNumber() {
            tvPrice.setText(stringBuilder.toString());
        }
    }
}
