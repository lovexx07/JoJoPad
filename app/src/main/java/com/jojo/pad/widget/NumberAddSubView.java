package com.jojo.pad.widget;

/**
 * Created by Administrator on 2018/1/2 0002.
 */

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jojo.pad.R;


public class NumberAddSubView extends LinearLayout implements View.OnClickListener {

    private TextView btn_sub;
    private TextView btn_add;
    private TextView tv_num;
    private Context mContext;

    /**
     * 设置默认值
     */
    private int value = 1;
    private int minValue = 1;
    private int maxValue = 5;

    public NumberAddSubView(Context context) {
        this(context, null);
    }

    public NumberAddSubView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public NumberAddSubView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView(context);
        //得到属性
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.NumberAddSubView, defStyleAttr, 0);
            int value = a.getInt(R.styleable.NumberAddSubView_value, 0);
            setValue(value);

            int maxValue = a.getInt(R.styleable.NumberAddSubView_maxValue, 0);
            setMaxValue(maxValue);

            int minValue = a.getInt(R.styleable.NumberAddSubView_minValue, 0);
            setMinValue(minValue);

            Drawable btnSubBackground = a.getDrawable(R.styleable.NumberAddSubView_btnSubBackground);
            if (btnSubBackground != null) {
                btn_sub.setBackground(btnSubBackground);
            }

            Drawable btnAddBackground = a.getDrawable(R.styleable.NumberAddSubView_btnAddBackground);
            if (btnAddBackground != null) {
                btn_sub.setBackground(btnAddBackground);
            }

            Drawable textViewBackground = a.getDrawable(R.styleable.NumberAddSubView_textViewBackground);
            if (textViewBackground != null) {
                tv_num.setBackground(textViewBackground);
            }

            a.recycle();

        }
    }

    private void initView(Context context) {
        //第三个参数：把当前View加载到NumberAddSubView控件上
        View.inflate(context, R.layout.number_add_sub_view, this);
        btn_sub = (TextView) findViewById(R.id.btn_sub);
        btn_add = (TextView) findViewById(R.id.btn_add);
        tv_num = (TextView) findViewById(R.id.tv_num);

        btn_sub.setOnClickListener(this);
        btn_add.setOnClickListener(this);
    }

    public int getValue() {
        String val = tv_num.getText().toString();
        if (!TextUtils.isEmpty(val)) {
            value = Integer.parseInt(val);
        }
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        tv_num.setText(value + "");
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_sub) {
            subNum();
            if (onButtonClickListenter != null) {
                onButtonClickListenter.onButtonSubClick(v, value);
            }
        } else if (v.getId() == R.id.btn_add) {
            addNum();
            if (onButtonClickListenter != null) {
                onButtonClickListenter.onButtonAddClick(v, value);
            }
        }
    }

    /**
     * 减少数据
     */
    private void subNum() {
        if (value > minValue) {
            value = value - 1;
            tv_num.setText(value + "");
        }
    }

    /**
     * 添加数据
     */
    private void addNum() {
        if (value < maxValue) {
            value = value + 1;
            tv_num.setText(value + "");
        }
    }

    public interface OnButtonClickListenter {
        /**
         * 当增加按钮被点击的时候回调该方法
         *
         * @param view
         * @param value
         */
        public void onButtonAddClick(View view, int value);

        /**
         * 当减少按钮被点击的时候回调这个方法
         *
         * @param view
         * @param value
         */
        public void onButtonSubClick(View view, int value);
    }

    private OnButtonClickListenter onButtonClickListenter;

    public void setOnButtonClickListenter(OnButtonClickListenter onButtonClickListenter) {
        this.onButtonClickListenter = onButtonClickListenter;
    }
}