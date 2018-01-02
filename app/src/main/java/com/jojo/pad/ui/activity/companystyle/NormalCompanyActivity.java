package com.jojo.pad.ui.activity.companystyle;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.jojo.pad.R;
import com.jojo.pad.base.BaseAcitivty;
import com.jojo.pad.dialog.NoIdGoodsPriceDialog;
import com.jojo.pad.listener.ViewClickListener;
import com.jojo.pad.dialog.MainMenuDialog;

import butterknife.BindView;

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
    ImageView ivHelp;
    @BindView(R.id.search_view)
    SearchView searchView;
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
    private ViewClickListener menuListener;

    @Override
    public int getLayoutId() {
        return R.layout.activity_normal_company;
    }

    @Override
    public void initView() {

    }

    @Override
    public void setListener() {
        mainSet.setOnClickListener(this);
        ivInput.setOnClickListener(this);

        menuListener = new ViewClickListener() {
            @Override
            public void clickListener(String msg, int type) {
                ToastUtils.showShort(msg);
            }
        };
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
            default:
                break;
        }
    }
}
