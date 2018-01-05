package com.jojo.pad.ui.activity;

import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.blankj.utilcode.util.ToastUtils;
import com.jojo.pad.R;
import com.jojo.pad.base.BaseAcitivty;
import com.jojo.pad.constant.MenuItem;
import com.jojo.pad.widget.PadHeader;

import butterknife.BindView;

public class GoodsManageActivity extends BaseAcitivty {
    @BindView(R.id.header)
    PadHeader header;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.bt_next)
    Button btNext;
    private String type;

    @Override
    public int getLayoutId() {
        return R.layout.activity_goods_manage;
    }

    @Override
    public void initView() {
        type = getIntent().getStringExtra("type");
        switch (type) {
            case MenuItem.inventory:
                header.setBackContent("盘点");
                break;
            case MenuItem.orderApplication:
                header.setBackContent("订货申请");
                break;
            case MenuItem.condiments:
                header.setBackContent("调货");
                break;
            case MenuItem.reportLoss:
                header.setBackContent("报损");
                break;
            case MenuItem.returnGoods:
                header.setBackContent("退货");
                break;
            default:
                ToastUtils.showShort("错误");
                break;
        }
    }

    @Override
    public void setListener() {

    }

    @Override
    public void initData() {

    }

}
