package com.jojo.pad.ui.activity.companystyle;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jojo.pad.R;
import com.jojo.pad.adapter.NormalCompanyAdapter;
import com.jojo.pad.base.BaseAcitivty;
import com.jojo.pad.constant.Constant;
import com.jojo.pad.constant.HttpConstant;
import com.jojo.pad.constant.MenuItem;
import com.jojo.pad.dialog.GoodsSearchDialog;
import com.jojo.pad.dialog.MainMenuDialog;
import com.jojo.pad.dialog.MessageDialog;
import com.jojo.pad.dialog.NoIdGoodsPriceDialog;
import com.jojo.pad.evenbean.MemberEvenBean;
import com.jojo.pad.evenbean.OrderListRefreshEvent;
import com.jojo.pad.listener.ObjectClickListener;
import com.jojo.pad.listener.ResponseListener;
import com.jojo.pad.listener.ViewClickListener;
import com.jojo.pad.model.bean.OrderBean;
import com.jojo.pad.model.bean.result.GoodCodeListBean;
import com.jojo.pad.model.http.BaseHttp;
import com.jojo.pad.scaner.BarcodeScannerResolver;
import com.jojo.pad.ui.activity.CheckOutActivity;
import com.jojo.pad.ui.activity.GoodsManageActivity;
import com.jojo.pad.ui.activity.LogisticActivity;
import com.jojo.pad.ui.activity.MessageCenterActivity;
import com.jojo.pad.ui.activity.NewGoodsActivity;
import com.jojo.pad.ui.activity.OrderApplicationActivity;
import com.jojo.pad.ui.activity.SaleDocumesActivity;
import com.jojo.pad.ui.activity.SystemSetupActivity;
import com.jojo.pad.ui.activity.TransferActivity;
import com.jojo.pad.ui.activity.member.MemberAddActivity;
import com.jojo.pad.ui.activity.member.MemberDetailActivity;
import com.jojo.pad.ui.activity.member.MemberSearchActivity;
import com.jojo.pad.util.Convert;
import com.jojo.pad.widget.SearchView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @BindView(R.id.tv_chose_memeber)
    TextView tvChoseMemeber;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.ll_member_result)
    LinearLayout llMemberResult;

    private ViewClickListener menuListener;
    private ObjectClickListener<GoodCodeListBean.GoodCodeBean> objectClickListener;
    //扫码枪监听
    private BarcodeScannerResolver mBarcodeScannerResolver;

    private String cid, cname, crecharge;//会员id，会员名字，会员帐余额
    private List<OrderBean> datas;
    private NormalCompanyAdapter normalCompanyAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_normal_company;
    }

    @Override
    public void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(linearLayoutManager);
        datas = new ArrayList<>();
        normalCompanyAdapter = new NormalCompanyAdapter(datas);
        recyclerview.setAdapter(normalCompanyAdapter);

        menuListener = new ViewClickListener() {
            @Override
            public void clickListener(String msg, int type) {
                switch (type) {
                    case Constant.VIEW_CLICK_TYPE_NOID:
                        OrderBean bean = new OrderBean("无码商品", msg, "", "");
                        normalCompanyAdapter.addData(bean);
                        refreshSum();
                        break;
                    case Constant.VIEW_CLICK_TYPE_MENU:
                        checkMenu(msg);
                        break;
                    case Constant.VIEW_CLICK_TYPE_SEARCH:
                        if (!TextUtils.isEmpty(msg)) {
                            searchGoodById(msg);
                        }
                        break;
                    case Constant.VIEW_CLICK_TYPE_DIALOG_CONFIRM:
                        datas.clear();
                        normalCompanyAdapter.notifyDataSetChanged();
                        refreshSum();
                        break;
                    default:
                        LogUtils.e("ViewClickListener");
                        break;
                }
            }
        };
        objectClickListener = new ObjectClickListener<GoodCodeListBean.GoodCodeBean>() {
            @Override
            public void clickListener(GoodCodeListBean.GoodCodeBean goodCodeBean, int type) {
                OrderBean bean = new OrderBean(goodCodeBean.getGoods_name(), goodCodeBean.getGoods_price(), goodCodeBean.getBarcode(), goodCodeBean.getGid());
                normalCompanyAdapter.addData(bean);
                refreshSum();
            }
        };
    }

    private void checkMenu(String msg) {
        switch (msg) {
            case MenuItem.transfer:
                toActivity(TransferActivity.class);
                break;
            case MenuItem.openCash:
                showToast("打开钱箱命令");
                break;
            case MenuItem.returnGoods:
                toActivity(GoodsManageActivity.class, "type", MenuItem.returnGoods);
                break;
            case MenuItem.addMembers:
                toActivity(MemberAddActivity.class);
                break;
            case MenuItem.salesDocumes:
                toActivity(SaleDocumesActivity.class);
                break;
            case MenuItem.newGoods:
                toActivity(NewGoodsActivity.class);
                break;
            case MenuItem.inventory:
                toActivity(GoodsManageActivity.class, "type", MenuItem.inventory);
                break;
            case MenuItem.orderApplication:
                toActivity(OrderApplicationActivity.class);
                break;
            case MenuItem.condiments:
                toActivity(GoodsManageActivity.class, "type", MenuItem.condiments);
                break;
            case MenuItem.logisticNotification:
                toActivity(LogisticActivity.class);
                break;
            case MenuItem.newworkOrder:
                ToastUtils.showShort("开发中");
                break;
            case MenuItem.messageCenter:
                toActivity(MessageCenterActivity.class);
                break;
            case MenuItem.reportLoss:
                toActivity(GoodsManageActivity.class, "type", MenuItem.reportLoss);
                break;
            case MenuItem.systemSetup:
                toActivity(SystemSetupActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    public void setListener() {
        EventBus.getDefault().register(this);
        startScanListenr();
        mainSet.setOnClickListener(this);
        ivInput.setOnClickListener(this);
        ivMessage.setOnClickListener(this);
        ivHelp.setOnClickListener(this);
        llMember.setOnClickListener(this);
        llEditCollect.setOnClickListener(this);
        tvClear.setOnClickListener(this);
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
                if (llMemberResult.getVisibility() == View.VISIBLE){
                    Bundle intent = new Bundle();
                    intent.putString("cid", cid);
                    intent.putString("cname", cname);
                    intent.putString("crecharge",crecharge);
                    intent.putString("type","detail");
                    toActivity(MemberDetailActivity.class, intent);
                }else {
                    toActivity(MemberSearchActivity.class);
                }

                break;
            case R.id.ll_edit_collect:
                Bundle bundle = new Bundle();
                bundle.putSerializable("orders", (Serializable) datas);
                if (!TextUtils.isEmpty(cid)) {
                    bundle.putString("cid",cid);
                }
                toActivityForResult(CheckOutActivity.class,bundle,Constant.INTENT_FAR_RESULT_A);
                break;
            case R.id.tv_clear:
                showClearDialog();
                break;
            default:
                break;
        }
    }




    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Object event) {
        if (event instanceof MemberEvenBean){
            MemberEvenBean memberEvenBean = (MemberEvenBean) event;
            if (memberEvenBean.isCancle()){
                crecharge = "";
                cname = "";
                cid = "";

                tvChoseMemeber.setVisibility(View.VISIBLE);
                llMemberResult.setVisibility(View.GONE);
            }else {
                crecharge = memberEvenBean.getCrecharge();
                cname = memberEvenBean.getCname();
                cid = memberEvenBean.getCid();

                tvChoseMemeber.setVisibility(View.GONE);
                llMemberResult.setVisibility(View.VISIBLE);
            }
            tvName.setText("姓名："+cname);
            tvAccount.setText("余额：￥"+crecharge);
        }else if (event instanceof  OrderBean){
            OrderBean orderBean = (OrderBean) event;
            datas.remove(orderBean);
            normalCompanyAdapter.notifyDataSetChanged();
            refreshSum();
        } else if (event instanceof OrderListRefreshEvent){
            refreshSum();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case Constant.INTENT_FAR_RESULT_A:
                    datas.clear();
                    normalCompanyAdapter.notifyDataSetChanged();
                    refreshSum();
                    break;
                default:
                    LogUtils.e("onActivityResult");
            }
        }
    }
    private void refreshSum(){
        int count =0;
        double sum=0;
        for (OrderBean orderBean :datas){
            count += orderBean.getCount();
            sum += Double.parseDouble(orderBean.getGoods_price()) * orderBean.getCount();
        }
        tvCount.setText(count+"");
        tvSum.setText(sum+"");
        tvCollect.setText("￥"+sum);

        if (count >0){
            tvClear.setVisibility(View.VISIBLE);
        }else {
            tvClear.setVisibility(View.INVISIBLE);
        }
    }

    private void searchGoodById(String code) {
        Map<String, String> map = new HashMap<>();
        map.put("code", code);
        BaseHttp.getJson(HttpConstant.Api.goodSearchByCode, map, activity, new ResponseListener() {
            @Override
            public void onSuccess(Object result) {
                GoodCodeListBean goodCodeListBean = Convert.fromJObject(result,GoodCodeListBean.class);
                if (goodCodeListBean.getData() != null) {
                    if (goodCodeListBean.getData().size() == 1) {
                        GoodCodeListBean.GoodCodeBean goodCodeBean = goodCodeListBean.getData().get(0);
                        OrderBean bean = new OrderBean(goodCodeBean.getGoods_name(), goodCodeBean.getGoods_price(), goodCodeBean.getBarcode(), goodCodeBean.getGid());
                        normalCompanyAdapter.addData(bean);
                    } else {
                        GoodsSearchDialog dialog = new GoodsSearchDialog.Builder(activity).search(goodCodeListBean.getData()).setListener(objectClickListener).create();
                        dialog.setCanceledOnTouchOutside(true);
                        dialog.show();
                    }
                }
            }

            @Override
            public void onError(String result) {
                ToastUtils.showShort(result);
            }
        });
    }

    private void showClearDialog() {
        MessageDialog messageDialog = new MessageDialog.Builder(mContext).setMessage("确认清空商品?").setTitle("提示")
                .setNegativeButton(menuListener).setPositiveButton(menuListener).create();
        messageDialog.setCanceledOnTouchOutside(true);
        messageDialog.show();
    }

    /**
     * 开始扫码监听按钮
     */
    public void startScanListenr() {
        mBarcodeScannerResolver = new BarcodeScannerResolver();
        mBarcodeScannerResolver.setScanSuccessListener(new BarcodeScannerResolver.OnScanSuccessListener() {
            @Override
            public void onScanSuccess(String barcode) {
                searchView.setEt_inputValue(barcode);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        removeScanListen();
    }

    /**
     * 移除扫码监听
     */
    public void removeScanListen() {
        mBarcodeScannerResolver.removeScanSuccessListener();
        mBarcodeScannerResolver = null;
    }

    /**
     * 扫码枪是输入设备，检测是否有外接输入设备.(这样判断其实并不严格)
     *
     * @return
     */
    private boolean hasScanGun() {
        Configuration cfg = getResources().getConfiguration();
        return cfg.keyboard != Configuration.KEYBOARD_NOKEYS;
    }

    /**
     * Activity截获按键事件.发给 BarcodeScannerResolver
     * dispatchKeyEvent() 和 onKeyDown() 方法均可
     *
     * @param event
     * @return
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (hasScanGun()) {
            if (mBarcodeScannerResolver != null) {
                mBarcodeScannerResolver.resolveKeyEvent(event);
            }
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mBarcodeScannerResolver != null) {
            mBarcodeScannerResolver.resolveKeyEvent(event);
        }
        return super.onKeyDown(keyCode, event);
    }

}
