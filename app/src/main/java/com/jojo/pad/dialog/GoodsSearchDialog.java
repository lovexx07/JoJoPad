package com.jojo.pad.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jojo.pad.R;
import com.jojo.pad.adapter.GoodsSearchAdapter;
import com.jojo.pad.constant.Constant;
import com.jojo.pad.listener.ObjectClickListener;
import com.jojo.pad.listener.ViewClickListener;
import com.jojo.pad.model.bean.result.GoodSearchListBean;
import com.jojo.pad.widget.SearchView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

        private Activity activity;
        private ObjectClickListener<GoodSearchListBean.GoodsListBean> listener;
        private GoodsSearchAdapter adapter;
        private List<GoodSearchListBean.GoodsListBean> datas = new ArrayList<>();

        public Builder(Activity activity) {
            this.activity = activity;
        }

        public Builder setListener(ObjectClickListener listener) {
            this.listener = listener;
            return this;
        }

        public Builder search(Collection<GoodSearchListBean.GoodsListBean> resource) {
            datas.addAll(resource);
            return this;
        }

        public GoodsSearchDialog create() {

            LayoutInflater inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final GoodsSearchDialog dialog = new GoodsSearchDialog(activity, R.style.Dialog);
            View layout = inflater.inflate(R.layout.dialog_goods_layout, null);
            dialog.addContentView(layout, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));


            Window window = dialog.getWindow();

            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = ScreenUtils.getScreenWidth() / 3;
            lp.height = ScreenUtils.getScreenHeight() / 10 * 9;
            window.setAttributes(lp);

            ButterKnife.bind(this, dialog);

            ivClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            searchview.setAutoSearchListener(new ViewClickListener() {
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
            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    if (listener != null) {
                        listener.clickListener(datas.get(position), Constant.VIEW_CLICK_TYPE_SEARCH_DIALOG);
                    }
                    dialog.dismiss();
                }
            });
            dialog.setContentView(layout);
            return dialog;
        }

        private void searchGoodById(String code) {
            if (TextUtils.isEmpty(code)) {
                adapter.setNewData(datas);
                return;
            }
            List<GoodSearchListBean.GoodsListBean> newdatas = new ArrayList<>();
            for (GoodSearchListBean.GoodsListBean bean : datas) {
                if ((!TextUtils.isEmpty(bean.getGoods_name()) && bean.getGoods_name().contains(code))
                        || (!TextUtils.isEmpty(bean.getBarcode()) && bean.getBarcode().contains(code))) {
                    newdatas.add(bean);
                }
            }
            adapter.setNewData(newdatas);
        }
    }

}
