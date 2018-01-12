package com.jojo.pad.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jojo.pad.R;
import com.jojo.pad.constant.Constant;
import com.jojo.pad.listener.ViewClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ruifeng on 2018/1/12.
 */

public class MessageDialog extends Dialog {
    public MessageDialog(@NonNull Context context) {
        super(context);
    }

    public MessageDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected MessageDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder implements View.OnClickListener {
        @BindView(R.id.iv_close)
        ImageView ivClose;
        @BindView(R.id.tv_message)
        TextView tvMessage;
        @BindView(R.id.tv_cancle)
        TextView tvCancle;
        @BindView(R.id.tv_confirm)
        TextView tvConfirm;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        private Context context;
        private String title = "", message = "";
        private MessageDialog dialog;

        private ViewClickListener positiveListener,negativeListener;



        public Builder(Context context) {
            this.context = context;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setPositiveButton(ViewClickListener listener){
            positiveListener = listener;
            return this;
        }
        public Builder setNegativeButton(ViewClickListener listener){
            negativeListener = listener;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public MessageDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            dialog = new MessageDialog(context, R.style.Dialog);
            View layout = inflater.inflate(R.layout.dialog_message_layout, null);

            dialog.addContentView(layout,
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            ButterKnife.bind(this, dialog);

            tvMessage.setText(message);
            tvTitle.setText(title);

            ivClose.setOnClickListener(this);
            tvCancle.setOnClickListener(this);
            tvConfirm.setOnClickListener(this);


            dialog.setContentView(layout);
            return dialog;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.iv_close:
                    break;
                case R.id.tv_cancle:
                    if (negativeListener != null){
                        negativeListener.clickListener(null, Constant.VIEW_CLICK_TYPE_DIALOG_CANCLE);
                    }
                    break;
                case R.id.tv_confirm:
                    if (positiveListener != null){
                        positiveListener.clickListener(null, Constant.VIEW_CLICK_TYPE_DIALOG_CONFIRM);
                    }
                    break;
                default:
                    break;
            }
            dialog.dismiss();
        }
    }
}
