package com.jojo.pad.uu;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jojo.pad.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity2 extends AppCompatActivity {

    private static final String TAG = MainActivity2.class.getSimpleName();
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.bt_start)
    Button btStart;
    @BindView(R.id.bt_end)
    Button btEnd;

    private BarcodeScannerResolver mBarcodeScannerResolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        startScanListen();
        btEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Configuration cfg = getResources().getConfiguration();
                Toast.makeText(MainActivity2.this, "barcode: " + cfg.keyboard, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 点击开始扫码监听按钮
     *
     */
    public void startScanListen() {
        mBarcodeScannerResolver = new BarcodeScannerResolver();
        mBarcodeScannerResolver.setScanSuccessListener(new BarcodeScannerResolver.OnScanSuccessListener() {
            @Override
            public void onScanSuccess(String barcode) {
                //TODO 显示扫描内容
                Log.w(TAG, "barcode: " + barcode);
                Toast.makeText(MainActivity2.this, "barcode: " + barcode, Toast.LENGTH_SHORT).show();
                tvContent.setText(barcode);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeScanListen();
    }

    /**
     * 点击移除扫码监听按钮
     *
     */
    public void removeScanListen() {
        mBarcodeScannerResolver.removeScanSuccessListener();
        mBarcodeScannerResolver = null;
    }


    /**
     * 扫码枪是输入设备，检测是否有外接输入设备.(这样判断其实并不严格)
     *
     * @return
     */
    private boolean hasScanGun() {
        Configuration cfg = getResources().getConfiguration();
        return cfg.keyboard != Configuration.KEYBOARD_NOKEYS;
    }

//

    /**
     * Activity截获按键事件.发给 BarcodeScannerResolver
     * dispatchKeyEvent() 和 onKeyDown() 方法均可
     *
     * @param event
     * @return
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
//        Log.i(TAG, "dispatchKeyEvent");

        if (hasScanGun()) {
            if (mBarcodeScannerResolver != null) {
                mBarcodeScannerResolver.resolveKeyEvent(event);
            }
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mBarcodeScannerResolver != null) {
            mBarcodeScannerResolver.resolveKeyEvent(event);
        }

        return super.onKeyDown(keyCode, event);
    }
}