package com.jojo.pad.ui.activity.companystyle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jojo.pad.R;
import com.jojo.pad.base.BaseAcitivty;
import com.jojo.pad.constant.Constant;
import com.jojo.pad.dialog.GoodsSearchDialog;
import com.jojo.pad.dialog.MainMenuDialog;
import com.jojo.pad.dialog.NoIdGoodsPriceDialog;
import com.jojo.pad.listener.ViewClickListener;
import com.jojo.pad.ui.activity.MemberActivity;
import com.jojo.pad.ui.activity.TransferActivity;
import com.jojo.pad.widget.SearchView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 一般零售公司，商品类型较多
 */
public class NormalCompanyActivity extends BaseAcitivty implements View.OnClickListener {

    @BindView(R.id.main_set)
    ImageView mainSet;
    @BindView(R.id.iv_input)
    ImageView ivInput;
    @BindView(R.id.iv_message)
    ImageView ivMessage;
    @BindView(R.id.iv_link)
    ImageView ivLink;
    @BindView(R.id.iv_help)
    TextView ivHelp;
    @BindView(R.id.iv_card)
    ImageView ivCard;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.tv_sum)
    TextView tvSum;
    @BindView(R.id.tv_discount)
    TextView tvDiscount;
    @BindView(R.id.tv_clear)
    TextView tvClear;
    @BindView(R.id.tv_collect)
    TextView tvCollect;
    @BindView(R.id.ll_edit_collect)
    LinearLayout llEditCollect;
    @BindView(R.id.search_view)
    SearchView searchView;
    @BindView(R.id.ll_member)
    LinearLayout llMember;

    private ViewClickListener menuListener;

    @Override
    public int getLayoutId() {
        return R.layout.activity_normal_company;
    }

    @Override
    public void initView() {
        menuListener = new ViewClickListener() {
            @Override
            public void clickListener(String msg, int type) {
                switch (type) {
                    case Constant.VIEW_CLICK_TYPE_PRICE:
                        LogUtils.e(msg);
                        break;
                    case Constant.VIEW_CLICK_TYPE_MENU:
                        checkMenu(msg);
                        break;
                    case Constant.VIEW_CLICK_TYPE_SEARCH:
                        if (!TextUtils.isEmpty(msg)) {
                            GoodsSearchDialog dialog = new GoodsSearchDialog.Builder(mContext).search(msg).setListener(menuListener).create();
                            dialog.setCanceledOnTouchOutside(true);
                            dialog.show();
                        }
                        break;
                    case Constant.VIEW_CLICK_TYPE_SEARCH_DIALOG:
                        LogUtils.e("ID : " + msg);
                        break;
                    default:
                        LogUtils.e("ViewClickListener");
                        break;
                }
            }
        };
    }

    private void checkMenu(String msg) {
        switch (msg) {
            case "交接班":
                toActivity(TransferActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    public void setListener() {
        mainSet.setOnClickListener(this);
        ivInput.setOnClickListener(this);
        ivMessage.setOnClickListener(this);
        ivHelp.setOnClickListener(this);
        llMember.setOnClickListener(this);

        searchView.setSearchListener(menuListener);

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_set:
                MainMenuDialog dialog = new MainMenuDialog.Builder(mContext).setListener(menuListener).create();
                dialog.setCanceledOnTouchOutside(true);
                dialog.show();
                break;
            case R.id.iv_input:
                NoIdGoodsPriceDialog goodsPriceDialog = new NoIdGoodsPriceDialog.Builder(mContext).setListener(menuListener).create();
                goodsPriceDialog.setCanceledOnTouchOutside(true);
                goodsPriceDialog.show();
                break;
            case R.id.iv_message:
                ToastUtils.showShort("暂无网店订单，货物信息或消息通知");
                break;
            case R.id.iv_help:
                ToastUtils.showShort(R.string.help);
                break;
            case R.id.ll_member:
                toActivityForResult(MemberActivity.class, null, Constant.INTENT_FAR_RESULT_A);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case Constant.INTENT_FAR_RESULT_A:

                    break;
                default:
                    LogUtils.e("onActivityResult");
            }
        }
    }
}
