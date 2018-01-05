package com.jojo.pad.dialog;


import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.blankj.utilcode.util.ScreenUtils;
import com.jojo.pad.R;
import com.jojo.pad.constant.Constant;
import com.jojo.pad.listener.ViewClickListener;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/1/5 0005.
 */

public class DatePickDialog extends Dialog {

    public DatePickDialog(@NonNull Context context) {
        super(context);
    }

    public DatePickDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected DatePickDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.datepicker)
        DatePicker datepicker;
        @BindView(R.id.tv_confirm)
        TextView tvConfirm;
        @BindView(R.id.timepicker)
        TimePicker timepicker;
        @BindView(R.id.ll_root)
        LinearLayout llRoot;
        private Context context;

        private String title ="";
        private ViewClickListener listener;
        private DatePickDialog dialog;

        private boolean showtimepicker = false;
        private Calendar cl;
        private int myear, mmonth, mday, mhour, mminute;


        public Builder(Context context) {
            this.context = context;
        }

        public Builder setListener(ViewClickListener listener) {
            this.listener = listener;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public DatePickDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            dialog = new DatePickDialog(context, R.style.Dialog);
            View layout = inflater.inflate(R.layout.dialog_date_picker_layout, null);

            dialog.addContentView(layout,
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            Window window = dialog.getWindow();

            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = ScreenUtils.getScreenWidth() / 5 * 3;
            lp.height = ScreenUtils.getScreenHeight() / 3 * 2;
            window.setAttributes(lp);

            ButterKnife.bind(this, dialog);
            tvTitle.setText(title);
            timepicker.setIs24HourView(true);


            initCalender();
            initListener();
            dialog.setContentView(layout);
            return dialog;
        }

        private void initListener() {
            datepicker.init(myear, cl.get(Calendar.MONTH), mday, new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                    myear = year;
                    mmonth = monthOfYear + 1;
                    mday = dayOfMonth;
                }
            });
            timepicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {

                @Override
                public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                    mhour = hourOfDay;
                    mminute = minute;
                }
            });

            tvConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (showtimepicker) {
                        listener.clickListener(myear + "-" + mmonth + "-" + mday + " " + mhour + ":" + mminute, Constant.VIEW_CLICK_TYPE_DATE_PICKER);
                        dialog.dismiss();
                    } else {
                        datepicker.setVisibility(View.GONE);
                        timepicker.setVisibility(View.VISIBLE);
                        showtimepicker = true;
                        Animation animation = new ScaleAnimation(0.5f, 1f, 0.5f, 1f,
                                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        //动画时间
                        animation.setDuration(300);
                        //动画的重复次数
                        animation.setRepeatCount(0);
                        //设置为true，动画转化结束后被应用
                        animation.setFillAfter(true);
                        //开始动画
                        llRoot.startAnimation(animation);

                    }
                }
            });
        }

        private void initCalender() {
            cl = Calendar.getInstance();
            myear = cl.get(Calendar.YEAR);
            mmonth = cl.get(Calendar.MONTH) + 1;
            mday = cl.get(Calendar.DAY_OF_MONTH);
            mhour = cl.get(Calendar.HOUR_OF_DAY);
            mminute = cl.get(Calendar.MINUTE);
        }
    }
}