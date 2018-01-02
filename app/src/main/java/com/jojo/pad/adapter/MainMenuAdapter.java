package com.jojo.pad.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jojo.pad.R;
import com.jojo.pad.base.AbstractBaseAdapter;
import com.jojo.pad.base.ViewHolder;
import com.jojo.pad.model.bean.MainMenuBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/2 0002.
 */

public class MainMenuAdapter extends AbstractBaseAdapter<MainMenuBean> {

    public MainMenuAdapter(List<MainMenuBean> list, Context context) {
        super(list, context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.getHolder(context, R.layout.item_main_menu_layout,convertView,parent);

        ImageView imageView = holder.getItemView(R.id.image);
        TextView title = holder.getItemView(R.id.title);

        MainMenuBean bean= getItem(position);

        imageView.setImageResource(bean.getResid());
        title.setText(bean.getTitle());

        return holder.getmConvertView();
    }
}
