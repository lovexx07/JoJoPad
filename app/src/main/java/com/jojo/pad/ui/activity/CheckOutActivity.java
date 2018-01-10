package com.jojo.pad.ui.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.jojo.pad.R;
import com.jojo.pad.base.BaseAcitivty;
import com.jojo.pad.constant.Constant;
import com.jojo.pad.listener.ViewClickListener;
import com.jojo.pad.widget.CheckOutRoot;
import com.jojo.pad.widget.DiscountSelectView;

import butterknife.BindView;

public class CheckOutActivity extends BaseAcitivty implements View.OnClickListener {
    @BindView(R.id.tv_discount_number)
    TextView tvDiscountNumber;
    @BindView(R.id.tv_discount)
    TextView tvDiscount;
    @BindView(R.id.tv_cash)
    RadioButton tvCash;
    @BindView(R.id.tv_weixin)
    RadioButton tvWeixin;
    @BindView(R.id.tv_zhifubao)
    RadioButton tvZhifubao;
    @BindView(R.id.tv_card)
    RadioButton tvCard;
    @BindView(R.id.tv_bank)
    RadioButton tvBank;
    @BindView(R.id.gadiogroup)
    RadioGroup gadiogroup;
    Button btnDetail;
    @BindView(R.id.tv_confirm)
    Button tvConfirm;
    @BindView(R.id.tv_discount_title)
    TextView tvDiscountTitle;
    @BindView(R.id.tv_discount_count)
    TextView tvDiscountCount;
    @BindView(R.id.tv_documents)
    CheckBox tvDocuments;
    @BindView(R.id.checkout)
    CheckOutRoot checkout;
    @BindView(R.id.tv_order_discount)
    TextView tvOrderDiscount;
    @BindView(R.id.tv_order_sum)
    TextView tvOrderSum;
    @BindView(R.id.tv_truenleft)
    TextView tvTruenleft;
    @BindView(R.id.tv_more)
    TextView tvMore;
    @BindView(R.id.ll_discount)
    LinearLayout llDiscount;
    @BindView(R.id.ll_discount_item)
    LinearLayout llDiscountItem;


    @BindView(R.id.tv_pay_type)
    TextView tvPayType;
    @BindView(R.id.tv_pay_end)
    TextView tvPayEnd;
    @BindView(R.id.tv_repay_money)
    TextView tvRepayMoney;
    @BindView(R.id.ll_root)
    LinearLayout llRoot;

    private PopupWindow mPopWindow;
    private DiscountSelectView discountSelect;
    private boolean isfirstshowdiscount = true;

    @Override
    public int getLayoutId() {
        return R.layout.activity_check_outctivity;
    }

    @Override
    public void initView() {

    }

    @Override
    public void setListener() {
        checkout.setViewClickListener(new ViewClickListener() {
            @Override
            public void clickListener(String msg, int type) {
                if (type == Constant.VIEW_CLICK_TYPE_NUMBER) {
                    tvOrderSum.setText(msg);
                } else if (type == Constant.VIEW_CLICK_TYPE_COMFIRM) {
                    finish();
                }
            }
        });


        tvTruenleft.setOnClickListener(this);
        tvDiscountNumber.setOnClickListener(this);
        tvDiscount.setOnClickListener(this);
        tvMore.setOnClickListener(this);
        llRoot.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        if (mPopWindow != null && mPopWindow.isShowing()) {
            mPopWindow.dismiss();
        }
        switch (v.getId()) {
            case R.id.ll_root:
                break;
            case R.id.tv_truenleft:
                tvDiscountTitle.setText("折扣率:");
                llDiscount.setVisibility(View.GONE);
                llDiscountItem.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_discount_number:
                tvDiscountTitle.setText("优惠券码:");
                llDiscountItem.setVisibility(View.GONE);
                llDiscount.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_discount:
                llDiscountItem.setVisibility(View.GONE);
                llDiscount.setVisibility(View.VISIBLE);
                showPopupWindow();
                break;
            case R.id.tv_more:
                showPopupWindow();
                break;
            default:
                break;
        }

    }


    private void showPopupWindow() {

        if (mPopWindow == null) {
            View contentView = LayoutInflater.from(mContext).inflate(R.layout.pop_discount_layout, null);
            mPopWindow = new PopupWindow(contentView);
            mPopWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
            mPopWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

            discountSelect = contentView.findViewById(R.id.discount_select);
            discountSelect.setViewClickListener(new ViewClickListener() {
                @Override
                public void clickListener(String msg, int type) {
                    mPopWindow.dismiss();
                    if (type == Constant.VIEW_CLICK_TYPE_NUMBER){
                        tvDiscountCount.setText(msg);
                    }else if (type == Constant.VIEW_CLICK_TYPE_COMFIRM){
                        ToastUtils.showShort("抹零");
                    }

                }
            });
        }

        if (isfirstshowdiscount) {
            isfirstshowdiscount = false;
            tvDiscountCount.getHandler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mPopWindow.showAsDropDown(tvDiscountCount);
                }
            },800);
        }else{
            mPopWindow.showAsDropDown(tvDiscountCount);
        }
    }

}
