package com.jojo.pad.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jojo.pad.R;
import com.jojo.pad.adapter.MainMenuAdapter;
import com.jojo.pad.constant.Constant;
import com.jojo.pad.constant.MenuItem;
import com.jojo.pad.listener.ViewClickListener;
import com.jojo.pad.model.bean.MainMenuBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2018/1/2 0002.
 */

public class MainMenuDialog extends Dialog {
    public MainMenuDialog(@NonNull Context context) {
        super(context);
    }

    public MainMenuDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected MainMenuDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder {
        @BindView(R.id.iv_close)
        ImageView ivClose;
        @BindView(R.id.gridview)
        GridView gridview;
        private String[] titles = new String[]{
                MenuItem.transfer, MenuItem.openCash, MenuItem.returnGoods, MenuItem.addMembers, MenuItem.salesDocumes,
                MenuItem.newGoods, MenuItem.inventory, MenuItem.orderApplication, MenuItem.condiments, MenuItem.logisticNotification,
                MenuItem.newworkOrder, MenuItem.messageCenter, MenuItem.reportLoss, MenuItem.systemSetup
        };

        private Integer[] images = {
                R.drawable.app_logo, R.drawable.app_logo,
                R.drawable.app_logo, R.drawable.app_logo,
                R.drawable.app_logo, R.drawable.app_logo,
                R.drawable.app_logo, R.drawable.app_logo,
                R.drawable.app_logo, R.drawable.app_logo,
                R.drawable.app_logo, R.drawable.app_logo,
                R.drawable.app_logo, R.drawable.app_logo,
        };

        private Context context;
        private ViewClickListener listener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setListener(ViewClickListener listener) {
            this.listener = listener;
            return this;
        }

        public MainMenuDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final MainMenuDialog dialog = new MainMenuDialog(context, R.style.Dialog);
            View layout = inflater.inflate(R.layout.dialog_mainmenu_layout, null);
            dialog.addContentView(layout,
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            ButterKnife.bind(this, dialog);

            List<MainMenuBean> list = new ArrayList<>();
            for (int i = 0; i < titles.length; i++) {
                MainMenuBean bean = new MainMenuBean(images[i], titles[i], i);
                list.add(bean);
            }

            final MainMenuAdapter mainMenuAdapter = new MainMenuAdapter(list, context);
            gridview.setAdapter(mainMenuAdapter);
            gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    listener.clickListener(mainMenuAdapter.getItem(position).getTitle(), Constant.VIEW_CLICK_TYPE_MENU);
                    dialog.dismiss();
                }
            });
            ivClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });


            dialog.setContentView(layout);
            return dialog;
        }
    }
}
