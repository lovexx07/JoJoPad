package com.jojo.pad.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jojo.pad.R;
import com.jojo.pad.adapter.SaleDocumentGoodsAdapter;
import com.jojo.pad.adapter.SaleDocumentOrderAdapter;
import com.jojo.pad.base.BaseAcitivty;
import com.jojo.pad.constant.HttpConstant;
import com.jojo.pad.listener.ResponseListener;
import com.jojo.pad.model.bean.result.SaleListBean;
import com.jojo.pad.model.http.BaseHttp;
import com.jojo.pad.util.AccountUtil;
import com.jojo.pad.util.Convert;
import com.jojo.pad.widget.SearchView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 销售单据
 */
public class SaleDocumesActivity extends BaseAcitivty {
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_order_id)
    TextView tvOrderId;
    @BindView(R.id.iv_help)
    TextView ivHelp;
    @BindView(R.id.searchview)
    SearchView searchview;
    @BindView(R.id.recyclerview_order)
    RecyclerView recyclerviewOrder;
    @BindView(R.id.tv_remarks)
    TextView tvRemarks;
    @BindView(R.id.recyclerview_good)
    RecyclerView recyclerviewGood;
    @BindView(R.id.tv_replay)
    TextView tvReplay;
    @BindView(R.id.tv_submit_again)
    TextView tvSubmitAgain;
    @BindView(R.id.tv_print)
    TextView tvPrint;

    private SaleDocumentOrderAdapter saleDocumentOrderAdapter;
    private SaleDocumentGoodsAdapter saleDocumentGoodsAdapter;
    private List<SaleListBean.SalesRecordListBean> orderlist = new ArrayList<>();
    private List<SaleListBean.SalesRecordListBean.SalesListBean> goodslist = new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_sale_documes;
    }

    @Override
    public void initView() {
        LinearLayoutManager orderlinearLayoutManager = new LinearLayoutManager(mContext);
        orderlinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerviewGood.setLayoutManager(orderlinearLayoutManager);


        LinearLayoutManager goodslinearLayoutManager = new LinearLayoutManager(mContext);
        goodslinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerviewOrder.setLayoutManager(goodslinearLayoutManager);

        saleDocumentOrderAdapter = new SaleDocumentOrderAdapter(orderlist);
        recyclerviewOrder.setAdapter(saleDocumentOrderAdapter);


        saleDocumentGoodsAdapter = new SaleDocumentGoodsAdapter(goodslist);
        recyclerviewGood.setAdapter(saleDocumentGoodsAdapter);
    }

    @Override
    public void setListener() {
        saleDocumentOrderAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                saleDocumentGoodsAdapter.setNewData(orderlist.get(position).getSales_list());
            }
        });
    }

    @Override
    public void initData() {
        getSaleList();
    }

    private void getSaleList() {
        Map<String, String> map = new HashMap<>();
        map.put("store_id", AccountUtil.store_id);
        map.put("user_id", AccountUtil.user_id);
        BaseHttp.getJson(HttpConstant.Api.saleList, map, activity, new ResponseListener() {
            @Override
            public void onSuccess(Object result) {
                SaleListBean saleListBean = Convert.fromJObject(result, SaleListBean.class);
                saleDocumentOrderAdapter.addData(saleListBean.getSales_record_list());
                saleDocumentGoodsAdapter.addData(saleListBean.getSales_record_list().get(0).getSales_list());
            }

            @Override
            public void onError(String result) {
                ToastUtils.showShort(result);
            }
        });
    }
}
