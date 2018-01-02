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
import com.jojo.pad.listener.ViewClickListener;

/**
 * Created by Administrator on 2018/1/2 0002.
 */

public class NoIdGoodsPriceDialog extends Dialog {
    public NoIdGoodsPriceDialog(@NonNull Context context) {
        super(context);
    }

    public NoIdGoodsPriceDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected NoIdGoodsPriceDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder {
        private Context context;
        private ViewClickListener listener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setListener(ViewClickListener listener) {
            this.listener = listener;
            return this;
        }

        public NoIdGoodsPriceDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final NoIdGoodsPriceDialog dialog = new NoIdGoodsPriceDialog(context, R.style.Dialog);
            View layout = inflater.inflate(R.layout.dialog_price_layout, null);
            dialog.addContentView(layout,
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            ImageView close = dialog.findViewById(R.id.iv_close);
            final TextView tv_price = dialog.findViewById(R.id.tv_price);

            close.setOnClickListener(new View.OnClickListener() {
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
