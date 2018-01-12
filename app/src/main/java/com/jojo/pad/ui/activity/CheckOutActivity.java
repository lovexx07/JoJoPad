package com.jojo.pad.ui.activity;

import android.text.TextUtils;
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
import com.jojo.pad.constant.HttpConstant;
import com.jojo.pad.listener.ResponseListener;
import com.jojo.pad.listener.ViewClickListener;
import com.jojo.pad.model.bean.OrderBean;
import com.jojo.pad.model.bean.result.SaleAddResultBean;
import com.jojo.pad.model.http.BaseHttp;
import com.jojo.pad.util.AccountUtil;
import com.jojo.pad.util.Convert;
import com.jojo.pad.widget.CheckOutRoot;
import com.jojo.pad.widget.DiscountSelectView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private List<OrderBean> datas;
    private double sum = 0;
    private String cid;
    private int paytype = 2;//0货到付款 1微信支付 2现金 3银行卡 4支付宝 5储值卡 6欠款
    private List<String> saleBeanList;
    private double end = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_check_outctivity;
    }

    @Override
    public void initView() {
        datas = (ArrayList<OrderBean>) getIntent().getSerializableExtra("orders");
        saleBeanList = new ArrayList<>();
        if (getIntent().hasExtra("cid")) {
            cid = getIntent().getStringExtra("cid");
        }
        if (datas != null) {
            for (OrderBean orderBean : datas) {
                sum += Double.parseDouble(orderBean.getGoods_price()) * orderBean.getCount();
                Map<String,String> map = new HashMap<>();
                map.put("gid",orderBean.getGid());
                map.put("goods_number",orderBean.getCount()+"");
                map.put("msg","");
                map.put("discount","10");
                saleBeanList.add(Convert.toJson(map));
            }
            tvOrderSum.setText(sum + "");
            tvPayEnd.setText(sum + "");
        }
    }

    @Override
    public void setListener() {
        checkout.setSearchListener(new ViewClickListener() {
            @Override
            public void clickListener(String msg, int type) {
                if (type == Constant.VIEW_CLICK_TYPE_NUMBER) {
                    tvPayEnd.setText(msg);
                    double payend = Double.parseDouble(msg);
                     end = payend -sum;
                    tvRepayMoney.setText("" + end);
                } else if (type == Constant.VIEW_CLICK_TYPE_COMFIRM) {
                    if (end >=0) {
                        saleOrder();
                    }
                }
            }
        });


        tvTruenleft.setOnClickListener(this);
        tvDiscountNumber.setOnClickListener(this);
        tvDiscount.setOnClickListener(this);
        tvMore.setOnClickListener(this);
        llRoot.setOnClickListener(this);

        gadiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {

                    case R.id.tv_weixin:
                        paytype = 1;
                        tvPayType.setText("微信");
                        break;
                    case R.id.tv_cash:
                        paytype = 2;
                        tvPayType.setText("现金");
                        break;
                    case R.id.tv_zhifubao:
                        paytype = 4;
                        tvPayType.setText("支付宝");
                        break;
                    case R.id.tv_bank:
                        paytype = 3;
                        tvPayType.setText("银行卡");
                        break;
                    case R.id.tv_card:
                        paytype = 5;
                        tvPayType.setText("储值卡");
                        break;
                    default:
                        paytype = 2;
                        break;
                }
            }
        });
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
                    if (type == Constant.VIEW_CLICK_TYPE_NUMBER) {
                        tvDiscountCount.setText(msg);
                    } else if (type == Constant.VIEW_CLICK_TYPE_COMFIRM) {
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
            }, 800);
        } else {
            mPopWindow.showAsDropDown(tvDiscountCount);
        }
    }

    private void saleOrder() {
        Map<String, String> map = new HashMap<>();
        map.put("store_id", AccountUtil.store_id);
        map.put("user_id", AccountUtil.user_id);
        map.put("type", paytype+"");
        map.put("sale_list", Convert.toJson(datas));
        if (!TextUtils.isEmpty(cid)) {
            map.put("cid", cid);
        }
        BaseHttp.postJson(HttpConstant.Api.saleAdd, Convert.toJson(map), activity, new ResponseListener() {
            @Override
            public void onSuccess(Object result) {
                SaleAddResultBean resultBean = Convert.fromJObject(result,SaleAddResultBean.class);
                finish();
            }

            @Override
            public void onError(String result) {
                ToastUtils.showShort(result);
            }
        });
    }
}
