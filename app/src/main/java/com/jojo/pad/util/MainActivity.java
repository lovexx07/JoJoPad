package com.jojo.pad.util;

/**
 * Created by Administrator on 2018/1/7 0007.
 */

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jojo.pad.R;

import android_serialport_api.SerialPortFinder;


/**
 * @author qiwenming
 * @date 2015-11-2 下午1:38:37
 * @ClassName MainActivity
 * @Package com.qiwenming.scandemo
 * @Description:   扫描
 */
public class MainActivity extends Activity {

    /**
     * 内容
     */
    private TextView contentTv;
    /**
     * 硬件地址
     */
    private String address = "";
    /**
     * 波特率
     */
    private int baudRate = 0;
    private SerialPortFinder serailFinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contentTv = (TextView)findViewById(R.id.tv_content);
        serailFinder = new SerialPortFinder();
    }

    /**
     * 扫描
     * @param view
     */
    public void toScan(View view){
        //1.判断有没有硬件地址
        //2.判断有木有波特率
        //3.组装指令
        //4.发送指令
        //5.数据处理

        //1.判断有没有硬件地址
        if("".equals(address)){
            setDeviceAddress();
            return;
        }
        //2.判断有木有波特率
        if(baudRate==0){
            setDeviceBautRate();
            return;
        }

        //3.组装指令
        //4.发送指令
        SerialPortSendData sendData = new SerialPortSendData(address, baudRate, CommandConstants.SCAN_START,
                CommandConstants.SCAN_RSP_OK, CommandConstants.SCAN_RSP_FAIL,
                CommandConstants.SCAN_STOP, true);
        ScanUtils scanDevice = new ScanUtils();
        scanDevice.toSend(this, sendData, new ScanUtils.ScanReciverListener() {
            //5.数据处理

            @Override
            public void onReceived(String receviceStr) {
                String scanNo = StringUtils.convertHexToString(receviceStr);
                contentTv.setText(scanNo);
                Log.i("toScan_recevied", scanNo);
            }

            @Override
            public void onFail(String string) {
                Toast.makeText(MainActivity.this, "扫描失败，请重新扫描", 0).show();
            }

            @Override
            public void onErr(Exception e) {
                Toast.makeText(MainActivity.this, "扫描失败，请重新扫描", 0).show();
            }

        });
    }


    /**
     * 显示地址
     * @param v
     */
    public void setDeviceAddress(){
        OneColumDialog dialog = new OneColumDialog(this, getAllDevicesPath(),new SelectListener() {
            @Override
            public void selected(int position, String value) {
                address = value;
                toScan(null);
            }
        });
        dialog.show();
        dialog.setTile("设置硬件地址");
    }

    /**
     * 获取全部窗口地址
     *
     * @return
     */
    public List<String> getAllDevicesPath() {
        return Arrays.asList(serailFinder.getAllDevicesPath());
    }

    /**
     * 设置波特率
     */
    public void setDeviceBautRate(){
        OneColumDialog dialog = new OneColumDialog(this, getAllBautRate(),new SelectListener() {
            @Override
            public void selected(int position, String value) {
                baudRate = Integer.parseInt(value);
                toScan(null);
            }
        });
        dialog.show();
        dialog.setTile("设置波特率");
    }

    /**
     * 获取全部 波特率
     *
     * @return
     */
    public List<String> getAllBautRate() {
        return Arrays.asList(getResources().getStringArray(R.array.baudrates));
    }
}