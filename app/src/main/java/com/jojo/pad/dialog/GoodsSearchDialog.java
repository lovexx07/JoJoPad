package com.jojo.pad.dialog;

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
import com.jojo.pad.listener.ViewClickListener;
import com.jojo.pad.model.bean.GoodsSearchBean;
import com.jojo.pad.widget.SearchView;

import java.util.ArrayList;
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

        private String search_str ="";
        private Context context;
        private ViewClickListener listener;
        private GoodsSearchAdapter adapter;
        private List<GoodsSearchBean> datas;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setListener(ViewClickListener listener) {
            this.listener = listener;
            return this;
        }

        public Builder search(String any){
            search_str = any;
            return this;
        }

        public GoodsSearchDialog create() {

            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final GoodsSearchDialog dialog = new GoodsSearchDialog(context, R.style.Dialog);
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
                    search_str = msg;
                    getData();
                }
            });
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerview.setLayoutManager(linearLayoutManager);

            datas = new ArrayList<>();
            adapter = new GoodsSearchAdapter(datas);
            recyclerview.setAdapter(adapter);

            adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    if (listener != null){
                        listener.clickListener(datas.get(position).getId(), Constant.VIEW_CLICK_TYPE_SEARCH_DIALOG);
                    }
                }
            });
            getData();
            dialog.setContentView(layout);
            return dialog;
        }

        private void getData() {
            ToastUtils.showShort("查找 "+search_str);

            GoodsSearchBean bean1 = new GoodsSearchBean("123456","巧克力","3");
            GoodsSearchBean bean2 = new GoodsSearchBean("23896544","苹果","5");
            GoodsSearchBean bean3 = new GoodsSearchBean("8362447521214","肉","11.5");

            datas.add(bean1);
            datas.add(bean2);
            datas.add(bean3);

            adapter.notifyDataSetChanged();
        }
    }
}
