package com.jojo.pad.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.JsPromptResult;
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
import butterknife.ButterKnife;

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
    @BindView(R.id.tv_member)
    TextView tvMember;
    @BindView(R.id.tv_goods_count)
    TextView tvGoodsCount;
    @BindView(R.id.tv_goods_sum)
    TextView tvGoodsSum;
    @BindView(R.id.tv_goods_discount)
    TextView tvGoodsDiscount;
    @BindView(R.id.tv_goods_must)
    TextView tvGoodsMust;
    @BindView(R.id.tv_pay_type)
    TextView tvPayType;
    @BindView(R.id.tv_pay_end)
    TextView tvPayEnd;

    private TextView tvOrderTime,tvOrderOperator;
    private SaleDocumentOrderAdapter saleDocumentOrderAdapter;
    private SaleDocumentGoodsAdapter saleDocumentGoodsAdapter;
    private List<SaleListBean.SalesRecordListBean> orderlist = new ArrayList<>();
    private List<SaleListBean.SalesRecordListBean.SalesListBean> goodslist = new ArrayList<>();

    //已选中的
    private SaleListBean.SalesRecordListBean selectedOrderBean;
    private View goodsHeader;

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

        goodsHeader = View.inflate(mContext,R.layout.goods_list_header_layout,null);
        tvOrderTime = goodsHeader.findViewById(R.id.tv_order_time);
        tvOrderOperator = goodsHeader.findViewById(R.id.tv_order_operator);
        saleDocumentGoodsAdapter.addHeaderView(goodsHeader);

    }

    @Override
    public void setListener() {
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        saleDocumentOrderAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (selectedOrderBean != null) {
                    selectedOrderBean.setIsselect(false);
                }
                selectedOrderBean = orderlist.get(position);
                selectedOrderBean.setIsselect(true);
                saleDocumentOrderAdapter.notifyDataSetChanged();

                saleDocumentGoodsAdapter.setNewData(orderlist.get(position).getSales_list());

                setOrderDetail();

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
                selectedOrderBean = saleListBean.getSales_record_list().get(0);
                selectedOrderBean.setIsselect(true);
                saleDocumentOrderAdapter.addData(saleListBean.getSales_record_list());
                saleDocumentGoodsAdapter.addData(saleListBean.getSales_record_list().get(0).getSales_list());
                setOrderDetail();
            }

            @Override
            public void onError(String result) {
                ToastUtils.showShort(result);
            }
        });
    }
    private void setOrderDetail() {
        tvOrderId.setText("单号：" + selectedOrderBean.getAction_id());
        if (!"非会员".equals(selectedOrderBean.getName())) {
            tvMember.setText(selectedOrderBean.getName());
        }
        tvGoodsCount.setText("共"+selectedOrderBean.getSale_num()+"件");
        tvGoodsSum.setText("商品总额：￥"+selectedOrderBean.getPrices());
        tvGoodsDiscount.setText("-优惠："+"--");
        tvGoodsMust.setText("=应收：￥"+"--");
        tvPayType.setText(selectedOrderBean.getType_name()+"：￥"+"--");
        tvPayEnd.setText("=实收：￥"+"--");

        tvOrderTime.setText("收银："+selectedOrderBean.getShort_time());
        tvOrderOperator.setText("收银员："+selectedOrderBean.getOperator());
    }
}
