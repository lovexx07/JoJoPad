package com.jojo.pad.widget;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.KeyboardUtils;
import com.jojo.pad.R;
import com.jojo.pad.constant.Constant;
import com.jojo.pad.listener.ViewClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/1/3 0003.
 */

public class SearchView extends LinearLayout implements View.OnClickListener {
    @BindView(R.id.iv_search)
    ImageView iv_search;


    @BindView(R.id.et_input)
    EditText et_input;
    @BindView(R.id.iv_dele)
    ImageView iv_dele;

    private ViewClickListener listener;

    public SearchView(Context context) {
        this(context, null);
    }

    public SearchView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public SearchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);

        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SearchView, defStyleAttr, 0);
            String hint = a.getString(R.styleable.SearchView_hint);

            int gravity = a.getInt(R.styleable.SearchView_gravity, Gravity.CENTER);

            et_input.setHint(hint);
            et_input.setGravity(gravity);

        }
    }

    private void initView(final Context context) {
        //第三个参数：把当前View加载到NumberAddSubView控件上
        View.inflate(context, R.layout.widget_search, this);
        ButterKnife.bind(this);

        iv_search.setOnClickListener(this);
        iv_dele.setOnClickListener(this);
    }
    public void setEt_inputValue(String input) {
        et_input.setText(input);
        if (listener != null) {
            listener.clickListener(input, Constant.VIEW_CLICK_TYPE_SEARCH);
        }
    }
    public void setSearchString(String str){
        et_input.setText(str);
    }
    public String getSearchString(){
        return et_input.getText().toString();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_search:
                String input = et_input.getText().toString();
                if (listener != null) {
                    listener.clickListener(input, Constant.VIEW_CLICK_TYPE_SEARCH);
                }
                break;
            case R.id.iv_dele:
                et_input.setText("");
                break;
            default:
                break;
        }
    }

    public void setSearchListener(ViewClickListener listener) {
        this.listener = listener;
    }
}
