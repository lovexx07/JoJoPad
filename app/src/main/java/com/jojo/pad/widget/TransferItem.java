package com.jojo.pad.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jojo.pad.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/1/3 0003.
 */

public class TransferItem extends LinearLayout {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_num)
    TextView tvNum;
    private Context context;

    public TransferItem(Context context) {
        this(context, null);
    }

    public TransferItem(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TransferItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TransferItem, defStyleAttr, 0);
            String num = a.getString(R.styleable.TransferItem_num);
            String title = a.getString(R.styleable.TransferItem_title);

            tvTitle.setText(title);
            tvNum.setText(num);
        }
    }

    private void initView() {
        View.inflate(context, R.layout.item_transfer_layout, this);
        ButterKnife.bind(this);
    }
}
