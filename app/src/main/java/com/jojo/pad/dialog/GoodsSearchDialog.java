package com.jojo.pad.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jojo.pad.R;
import com.jojo.pad.adapter.GoodsSearchAdapter;
import com.jojo.pad.constant.Constant;
import com.jojo.pad.constant.HttpConstant;
import com.jojo.pad.listener.ObjectClickListener;
import com.jojo.pad.listener.ResponseListener;
import com.jojo.pad.listener.ViewClickListener;
import com.jojo.pad.model.bean.GoodsSearchBean;
import com.jojo.pad.model.bean.OrderBean;
import com.jojo.pad.model.bean.result.GoodCodeListBean;
import com.jojo.pad.model.http.BaseHttp;
import com.jojo.pad.util.Convert;
import com.jojo.pad.widget.SearchView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/1/3 0003.
 */

public class GoodsSearchDialog extends Dialog {
    public GoodsSearchDialog(@NonNull Context context) {
        super(context);
    }

    public GoodsSearchDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected GoodsSearchDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder {
        @BindView(R.id.iv_close)
        ImageView ivClose;
        @BindView(R.id.searchview)
        SearchView searchview;
        @BindView(R.id.recyclerview)
        RecyclerView recyclerview;

        private String search_str ="";
        private Activity activity;
        private ObjectClickListener<GoodCodeListBean.GoodCodeBean> listener;
        private GoodsSearchAdapter adapter;
        private List<GoodCodeListBean.GoodCodeBean> datas = new ArrayList<>();

        public Builder(Activity activity) {
            this.activity = activity;
        }

        public Builder setListener(ObjectClickListener listener) {
            this.listener = listener;
            return this;
        }

        public Builder search(Collection<GoodCodeListBean.GoodCodeBean> resource){
            datas.addAll(resource);
            return this;
        }

        public GoodsSearchDialog create() {

            LayoutInflater inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final GoodsSearchDialog dialog = new GoodsSearchDialog(activity, R.style.Dialog);
            View layout = inflater.inflate(R.layout.dialog_goods_layout, null);
            dialog.addContentView(layout, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            ButterKnife.bind(this, dialog);

            ivClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            searchview.setSearchListener(new ViewClickListener() {
                @Override
                public void clickListener(String msg, int type) {
                    searchGoodById(msg);
                }
            });
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerview.setLayoutManager(linearLayoutManager);
            adapter = new GoodsSearchAdapter(datas);
            recyclerview.setAdapter(adapter);

            adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    if (listener != null){
                        listener.clickListener(datas.get(position), Constant.VIEW_CLICK_TYPE_SEARCH_DIALOG);
                    }
                    dialog.dismiss();
                }
            });
            dialog.setContentView(layout);
            return dialog;
        }
        private void searchGoodById(String code) {
            Map<String, String> map = new HashMap<>();
            map.put("code", code);
            BaseHttp.getJson(HttpConstant.Api.goodSearchByCode, map,activity , new ResponseListener() {
                @Override
                public void onSuccess(Object result) {
                    GoodCodeListBean goodCodeListBean = Convert.fromJObject(result,GoodCodeListBean.class);
                    if (goodCodeListBean.getData() != null){
                        adapter.setNewData(goodCodeListBean.getData());
                    }
                }
            });
        }
    }

}
