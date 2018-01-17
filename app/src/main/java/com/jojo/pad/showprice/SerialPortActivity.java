package com.jojo.pad.showprice;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jojo.pad.R;
import com.kongqw.serialportlibrary.Device;
import com.kongqw.serialportlibrary.SerialPortManager;
import com.kongqw.serialportlibrary.listener.OnOpenSerialPortListener;

import java.io.File;

public class SerialPortActivity extends AppCompatActivity implements OnOpenSerialPortListener {

    private static final String TAG = SerialPortActivity.class.getSimpleName();


    private SerialPortManager mSerialPortManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serial_port);

        Device device = PriceShowUtil.getDevice();
        mSerialPortManager = new SerialPortManager();

//        // 打开串口
//        mSerialPortManager.setOnOpenSerialPortListener(this);
//                .setOnSerialPortDataListener(new OnSerialPortDataListener() {
//                    @Override
//                    public void onDataReceived(byte[] bytes) {
//                        final byte[] finalBytes = bytes;
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                showToast(String.format("接收\n%s", new String(finalBytes)));
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void onDataSent(byte[] bytes) {
//                        Log.i(TAG, "onDataSent [ byte[] ]: " + Arrays.toString(bytes));
//                        Log.i(TAG, "onDataSent [ String ]: " + new String(bytes));
//                        final byte[] finalBytes = bytes;
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                showToast(String.format("发送\n%s", new String(finalBytes)));
//                            }
//                        });
//                    }
//                });

        if (device != null){
            mSerialPortManager.openSerialPort(device.getFile(), 2400);
        }




        mSerialPortManager.sendBytes(EpsonPosPrinterCommand.ESC_INIT);
    }

    @Override
    protected void onDestroy() {
        if (null != mSerialPortManager) {
            mSerialPortManager.closeSerialPort();
            mSerialPortManager = null;
        }
        super.onDestroy();
    }

    /**
     * 串口打开成功
     *
     * @param device 串口
     */
    @Override
    public void onSuccess(File device) {
        Toast.makeText(getApplicationContext(), String.format("串口 [%s] 打开成功", device.getPath()), Toast.LENGTH_SHORT).show();
    }

    /**
     * 串口打开失败
     *
     * @param device 串口
     * @param status status
     */
    @Override
    public void onFail(File device, Status status) {
        switch (status) {
            case NO_READ_WRITE_PERMISSION:
                showDialog(device.getPath(), "没有读写权限");
                break;
            case OPEN_FAIL:
            default:
                showDialog(device.getPath(), "串口打开失败");
                break;
        }
    }

    /**
     * 显示提示框
     *
     * @param title   title
     * @param message message
     */
    private void showDialog(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("退出", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        finish();
                    }
                })
                .setCancelable(false)
                .create()
                .show();
    }



    /**
     * 发送数据
     *
     * @param view view
     */
    public void onSend(View view) {
        EditText editTextSendContent = (EditText) findViewById(R.id.et_send_content);
        if (null == editTextSendContent) {
            return;
        }
        String sendContent = editTextSendContent.getText().toString().trim();
        if (TextUtils.isEmpty(sendContent)) {
            Log.i(TAG, "onSend: 发送内容为 null");
            return;
        }
        mSerialPortManager.sendBytes(PriceShowUtil.getShowByte(sendContent,PriceShowUtil.DISPLAY_STATE_PRICE));
    }


}