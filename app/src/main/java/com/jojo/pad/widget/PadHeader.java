package com.jojo.pad.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.jojo.pad.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/1/4 0004.
 */

public class PadHeader extends LinearLayout implements View.OnClickListener {
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.iv_help)
    TextView ivHelp;
    @BindView(R.id.ll_root)
    LinearLayout llRoot;
    private Context context;
    OnClickListener backlistener;
    OnClickListener helplistener;
    OnClickListener datelistener;

    public PadHeader(Context context) {
        this(context, null);
    }

    public PadHeader(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public PadHeader(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PadHeader, defStyleAttr, 0);

            String title = a.getString(R.styleable.PadHeader_backcontent);
            int showdate = a.getInt(R.styleable.PadHeader_showdate, INVISIBLE);

            tvBack.setText(title);
            tvDate.setVisibility(showdate);

            Drawable btnSubBackground = a.getDrawable(R.styleable.PadHeader_background);
            if (btnSubBackground != null) {
                llRoot.setBackground(btnSubBackground);
            }
//
//            tvBack.setTextColor(textcolor);
//            tvDate.setTextColor(textcolor);
//            ivHelp.setTextColor(textcolor);
//
//            if (!isInEditMode()) {
//                if (textcolor == getResources().getColor(R.color.white)) {
//                    int pad = SizeUtils.dp2px(5);
//                    Drawable drback = getResources().getDrawable(R.mipmap.left_w);
//                    Drawable drhelp = getResources().getDrawable(R.mipmap.help_w);
//                    drback.setBounds(pad, pad, pad, pad);
//                    drhelp.setBounds(pad, pad, pad, pad);
//                    tvBack.setCompoundDrawablesWithIntrinsicBounds(drback, null, null, null);
//                    ivHelp.setCompoundDrawablesWithIntrinsicBounds(drhelp, null, null, null);
//                }
//            } else {
//                return;
//            }
        }
    }

    private void initView() {
        View.inflate(context, R.layout.pad_header, this);
        ButterKnife.bind(this);

        tvBack.setOnClickListener(this);
        tvDate.setOnClickListener(this);
        ivHelp.setOnClickListener(this);
    }

    public void setBackClickListener(OnClickListener listener) {
        this.backlistener = listener;
    }

    public void setHelpClickListener(OnClickListener listener) {
        this.helplistener = listener;
    }

    public void setDateClickListener(OnClickListener listener) {
        this.datelistener = listener;
    }

    public void setBackContent(String content){
        tvBack.setText(content);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                if (backlistener != null) {
                    backlistener.onClick(view);
                }
                break;
            case R.id.tv_date:
                if (datelistener != null) {
                    datelistener.onClick(view);
                }
                break;
            case R.id.iv_help:
                if (helplistener != null) {
                    helplistener.onClick(view);
                }
                break;
            default:
                break;
        }
    }
}
