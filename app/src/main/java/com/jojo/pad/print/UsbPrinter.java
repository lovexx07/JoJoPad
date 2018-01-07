package com.jojo.pad.print;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbConstants;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Administrator on 2018/1/7 0007.
 */

public class UsbPrinter {
    private Context context;
    private UsbManager usbManager;
    /**
     * 满足的设备
     */
    private UsbDevice myUsbDevice;
    /**
     * usb接口
     */
    private UsbInterface usbInterface;
    /**
     * 块输出端点
     */
    private UsbEndpoint epBulkOut;
    private UsbEndpoint epBulkIn;
    /**
     * 控制端点
     */
    private UsbEndpoint epControl;
    /**
     * 中断端点
     */
    private UsbEndpoint epIntEndpointOut;
    private UsbEndpoint epIntEndpointIn;
    /**
     * 连接
     */
    private UsbDeviceConnection myDeviceConnection;


    private PendingIntent mPermissionIntent;
    private static final String ACTION_USB_PERMISSION = "com.usb.printer.USB_PERMISSION";
    private BroadcastReceiver receiver;

    private UsbPrinter() {
    }

    public static UsbPrinter getInstance(){
        return new UsbPrinter();
    }
    public  void init(Context context) {
        this.context = context;
        //1)创建usbManager
        usbManager = (UsbManager) context.getSystemService(Context.USB_SERVICE);
        mPermissionIntent = PendingIntent.getBroadcast(context, 0, new Intent(ACTION_USB_PERMISSION), 0);
        initReceiver(context);


        //2)获取到所有设备 选择出满足的设备
        enumeraterDevices();
        //3)查找设备接口
        getDeviceInterface();
        //4)获取设备endpoint
        assignEndpoint();
//        //5)打开conn连接通道
        openDevice();


    }

    /**
     * 枚举设备
     */
    private void enumeraterDevices() {
        HashMap<String, UsbDevice> deviceList = usbManager.getDeviceList();
        Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();
        StringBuilder sb = new StringBuilder();
        while (deviceIterator.hasNext()) {
            UsbDevice device = deviceIterator.next();
            sb.append(devicesString(device));
            if (device.getVendorId() == 1155 && device.getProductId() == 1803) {
                myUsbDevice = device; // 获取USBDevice
                if (!usbManager.hasPermission(myUsbDevice)) {
                    usbManager.requestPermission(myUsbDevice, mPermissionIntent);
                }
            }
        }
    }

    /**
     * usb设备的信息
     *
     * @param device
     * @return
     */
    private String devicesString(UsbDevice device) {
        StringBuilder builder = new StringBuilder("UsbDevice\nName=" + device.getDeviceName() +
                "\nVendorId=" + device.getVendorId() + "\nProductId=" + device.getProductId() +
                "\nmClass=" + device.getClass() + "\nmSubclass=" + device.getDeviceSubclass() +
                "\nmProtocol=" + device.getDeviceProtocol() + "\nmManufacturerName=" + "\nmSerialNumber=" +
                "\n\n");
        return builder.toString();
    }

    /**
     * 获取设备的接口
     */
    private void getDeviceInterface() {
        if (myUsbDevice != null) {
            usbInterface = myUsbDevice.getInterface(0);
            System.out.println("成功获得设备接口:" + usbInterface.getId());
        }
    }

    /**
     * 分配端点，IN | OUT，即输入输出；可以通过判断
     */
    private void assignEndpoint() {
        if (usbInterface != null) {
            for (int i = 0; i < usbInterface.getEndpointCount(); i++) {
                UsbEndpoint ep = usbInterface.getEndpoint(i);
                switch (ep.getType()) {
                    case UsbConstants.USB_ENDPOINT_XFER_BULK://块
                        if (UsbConstants.USB_DIR_OUT == ep.getDirection()) {//输出
                            epBulkOut = ep;
                            System.out.println("Find the BulkEndpointOut," + "index:" + i + "," + "使用端点号：" + epBulkOut.getEndpointNumber());
                        } else {
                            epBulkIn = ep;
                            System.out.println("Find the BulkEndpointIn:" + "index:" + i + "," + "使用端点号：" + epBulkIn.getEndpointNumber());
                        }
                        break;
                    case UsbConstants.USB_ENDPOINT_XFER_CONTROL://控制
                        epControl = ep;
                        System.out.println("find the ControlEndPoint:" + "index:" + i + "," + epControl.getEndpointNumber());
                        break;
                    case UsbConstants.USB_ENDPOINT_XFER_INT://中断
                        if (ep.getDirection() == UsbConstants.USB_DIR_OUT) {//输出
                            epIntEndpointOut = ep;
                            System.out.println("find the InterruptEndpointOut:" + "index:" + i + "," + epIntEndpointOut.getEndpointNumber());
                        }
                        if (ep.getDirection() == UsbConstants.USB_DIR_IN) {
                            epIntEndpointIn = ep;
                            System.out.println("find the InterruptEndpointIn:" + "index:" + i + "," + epIntEndpointIn.getEndpointNumber());
                        }
                        break;
                    default:
                        break;
                }
            }
        }
    }

    /**
     * 连接设备
     */
    private void openDevice() {
        if (usbInterface != null) {//接口是否为null
            // 在open前判断是否有连接权限；对于连接权限可以静态分配，也可以动态分配权限
            UsbDeviceConnection conn = null;
            if (usbManager.hasPermission(myUsbDevice)) {
                //有权限，那么打开
                conn = usbManager.openDevice(myUsbDevice);
            }else {
                usbManager.requestPermission(myUsbDevice, mPermissionIntent);
            }
            if (null == conn) {
                Toast.makeText(context, "不能连接到设备", Toast.LENGTH_SHORT).show();
                return;
            }
            //打开设备
            if (conn.claimInterface(usbInterface, true)) {
                myDeviceConnection = conn;
                if (myDeviceConnection != null)// 到此你的android设备已经连上zigbee设备
                {
                    System.out.println("open设备成功！");
                }
                final String mySerial = myDeviceConnection.getSerial();
                System.out.println("设备serial number：" + mySerial);
            } else {
                System.out.println("无法打开连接通道。");
                Toast.makeText(context, "无法打开连接通道。", Toast.LENGTH_SHORT).show();
                conn.close();
            }
        }
    }

    /**
     * 发送数据
     *
     * @param buffer
     */
    public void sendMessageToPoint(byte[] buffer) {
        if (myDeviceConnection.bulkTransfer(epBulkOut, buffer, buffer.length, 0) >= 0) {
            //0 或者正数表示成功
//            Toast.makeText(context,"发送成功",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "打印失败", Toast.LENGTH_SHORT).show();
        }
    }


    private void initReceiver(Context context) {//授权对话框点击操作之后会发出系统广播
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (ACTION_USB_PERMISSION.equals(intent.getAction())) {
                    synchronized (this) {
                        UsbDevice device = (UsbDevice) intent
                                .getParcelableExtra(UsbManager.EXTRA_DEVICE);
                        boolean usbPremission = intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false);
                        if ((usbPremission) && (device != null)) {
                            closeDevice();


                            //5)打开conn连接通道
                            openDevice();
                        }

                    }
                    //有设备插入
                } else if (UsbManager.ACTION_USB_DEVICE_ATTACHED.equals(action)) {
                    UsbDevice usbDevice = (UsbDevice) intent
                            .getParcelableExtra(UsbManager.EXTRA_DEVICE);
                    if (usbDevice != null) {
                        if (!usbManager.hasPermission(usbDevice)) {
                            usbManager.requestPermission(usbDevice, mPermissionIntent);
                        }
                    }

                //有设备拔出
                } else if (UsbManager.ACTION_USB_DEVICE_DETACHED.equals(action)) {
                    UsbDevice usb = (UsbDevice) intent
                            .getParcelableExtra(UsbManager.EXTRA_DEVICE);
                    closeDevice();
                }

            }
        };

        IntentFilter ifilter = new IntentFilter(ACTION_USB_PERMISSION);
        ifilter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
        ifilter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED);
        context.registerReceiver(receiver, ifilter);
    }
    public void onDestory(){
        context.unregisterReceiver(receiver);
        receiver = null;
    }
    private void closeDevice() {

    }
}
