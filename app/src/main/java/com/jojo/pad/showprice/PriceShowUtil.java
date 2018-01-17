package com.jojo.pad.showprice;

import android.text.TextUtils;

import com.jojo.pad.util.printerCmdUtils;
import com.kongqw.serialportlibrary.Device;
import com.kongqw.serialportlibrary.SerialPortFinder;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Administrator on 2018/1/17 0017.
 */

public class PriceShowUtil {

    /** 状态灯全灭 */
    public static final char DISPLAY_STATE_OFF = '0';
    /** 单价状态灯 */
    public static final char DISPLAY_STATE_PRICE = '1';
    /** 总计状态灯 */
    public static final char DISPLAY_STATE_TOTAL = '2';
    /** 收款状态灯 */
    public static final char DISPLAY_STATE_AMOUNT = '3';
    /** 找零状态灯 */
    public static final char DISPLAY_STATE_CHAGNE = '4';


    public static Device getDevice(){
        SerialPortFinder serialPortFinder = new SerialPortFinder();
        ArrayList<Device> devices = serialPortFinder.getDevices();

        if (devices != null){
            for (Device device:devices){
                String deviceName = device.getName();
                File file = device.getFile();
                boolean canRead = file.canRead();
                boolean canWrite = file.canWrite();

                if (deviceName.equals("ttyS1") && canRead && canWrite){
                    return device;
                }
            }
        }
        return null;
    }
    public static byte[] getShowByte(String price,char state){
        if (TextUtils.isEmpty(price)) {
            return null;
        }

        byte[] sendContentBytes = EpsonPosPrinterCommand.sendDisplayData(price);
        byte[] stateBytes = EpsonPosPrinterCommand.setDisplayState(state);

        byte[][] bytes = {
                sendContentBytes,stateBytes
        };
        return printerCmdUtils.byteMerger(bytes);
    }
}
