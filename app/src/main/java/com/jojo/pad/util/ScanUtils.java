//package com.jojo.pad.util;
//
///**
// * Created by Administrator on 2018/1/7 0007.
// */
//
//import android.content.Context;
//import android.util.Log;
//
//
//import java.io.InputStream;
//import java.io.OutputStream;
//
//import android_serialport_api.SerialPort;
//import android_serialport_api.SerialPortFinder;
//
///**
// * @author qiwenming
// * @date 2015-11-2 下午2:00:12
// * @ClassName ScanUtils
// * @Package com.qiwenming.scandemo.utils
// * @Description: 串口工作类
// */
//public class ScanUtils {
//    protected static OutputStream mOutputStream=null;
//    private static InputStream mInputStream = null;
//    private ReadThread mReadThread;
//    public SerialPortFinder mSerialPortFinder = new SerialPortFinder();
//    private SerialPort mSerialPort;
//    private Context context;
//    public static ReadThread thread = null;
//    /**
//     * @author qiwenming
//     * @creation 2015-6-18 下午4:38:54
//     * @instruction 读取类
//     */
//    private class ReadThread extends Thread {
//        private ScanReciverListener listener;
//        private SerialPortSendData sendData;
//        public ReadThread() {
//        }
//
//        public void setDataListener(SerialPortSendData sendData, ScanReciverListener listener) {
//            this.listener = listener;
//            this.sendData = sendData;
//        }
//
//
//        @Override
//        public void run() {
//            thread = this;
//            StringBuffer sb = new StringBuffer();
//            super.run();
//            while (!isInterrupted()) {
//                int size;
//                try {
//                    byte[] buffer = new byte[64];
//                    if (mInputStream == null)
//                        return;
//                    size = mInputStream.read(buffer);
//                    if (size > 0) { // 读取数据 数据c
//                        //str 就是我们读取到数据
//                        String str = StringUtils.bytesToHexString(buffer, size).trim().toLowerCase();
//                        sb.append(str);
//                        String msg = sb.toString();
//                        //如果我们存储的数据，也就是读取到得数据包含了 结束的标志，那么进入这个if
//                        if (msg.contains(sendData.stopStr)) {
//                            //把成功的标志替换为 ""
//                            msg = msg.replaceFirst("("+sendData.okStr+")+", "");
//                            //查找到结束位标志的位置  然后窃取我们的数据（不包含结束位）
//                            int end1 = msg.indexOf(sendData.stopStr);
//                            if (end1 > 0) msg = msg.substring(0, end1 + 1);
//
//                            //查找到结束位标志的位置  然后窃取我们的数据（不包含结束位）有的时候我们可能出现两种结束位
//                            int end2 = msg.indexOf(sendData.stopStr1);
//                            if (end2 > 0) msg = msg.substring(0, end2 + 1);
//
//                            final String data = msg;
//                            Log.i("onDataReceived_stop", data);
//                            Log.i("onDataReceived_stop_ascii",StringUtils.convertHexToString(data)+"--------"+Thread.currentThread().getId());
//                            closeDevice();
//                            if (null == context)
//                                return;
//                            ((MainActivity) context)
//                                    .runOnUiThread(new Runnable() {
//                                        @Override
//                                        public void run() {
//                                            thread = null;
//                                            listener.onReceived(data);
//                                        }
//                                    });
//                        }
//                        //包含了 失败的字符标志
//                        if (sb.toString().matches("\\w+" + sendData.failStr + "\\w+")) {
//                            closeDevice();
//                            if (null == context)
//                                return;
//                            ((MainActivity) context)
//                                    .runOnUiThread(new Runnable() {
//                                        @Override
//                                        public void run() {
//                                            thread = null;
//                                            listener.onFail(sendData.failStr);
//                                        }
//                                    });
//                        }
//                    }
//                } catch (Exception e) {
//                    listener.onErr(e);
//                    return;
//                }
//            }
//        }
//    }
//
//    /**
//     * 发送数据
//     *
//     * @param context
//     * @param devStr
//     *            地址
//     * @param baudRate
//     *            波特率
//     * @param msg
//     *            发送的消息
//     */
//    public void toSend(Context context, SerialPortSendData sendData,
//                       ScanReciverListener listener) {
//        this.context = context;
//        //检查设备地址
//        if ("".equals(sendData.path) || "/dev/tty".equals(sendData.path)) {
//            Toast.makeText(context, "设备地址不能为空", 0).show();
//            return;
//        }
//        //检查指令
//        if ("".equals(sendData.commandStr)) {
//            Toast.makeText(context, "指令不能为空", 0).show();
//            return;
//        }
//        //获取输入输出流
//        try {
//            mSerialPort = getSerialPort(sendData.path, sendData.baudRate);
//            mOutputStream = mSerialPort.getOutputStream();
//            mInputStream = mSerialPort.getInputStream();
//            //使用一个线程来读取数据
//            if(thread==null){
//                thread = new ReadThread();
//                thread.setDataListener(sendData, listener);
//                thread.start();
//            }
//            else{
//                thread.setDataListener(sendData, listener);
//            }
//
//        } catch (SecurityException e) {
//        } catch (IOException e) {
//        } catch (InvalidParameterException e) {
//        }
//
//        // 上面是获取设置而已 下面这个才是发送指令
//        byte[] text = StringUtils.hexStringToBytes(sendData.commandStr);
//        try {
//            mOutputStream.write(text);
//            mOutputStream.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 获取到串口通信的一个实例
//     *
//     * @param path
//     * @param baudrate
//     * @return
//     * @throws SecurityException
//     * @throws IOException
//     * @throws InvalidParameterException
//     */
//    public SerialPort getSerialPort(String path, int baudrate)
//            throws SecurityException, IOException, InvalidParameterException {
//        /* Check parameters */
//        if ((path.length() == 0) || (baudrate == -1)) {
//            throw new InvalidParameterException();
//        }
//        /* Open the serial port */
//        mSerialPort = new SerialPort(new File(path), baudrate, 0);// 打开这个串口
//        return mSerialPort;
//    }
//
//    /**
//     * 关闭设备
//     */
//    public void closeDevice() {
//        if (mReadThread != null)
//            mReadThread.interrupt();
//        closeSerialPort();
//        // mSerialPort = null;
//    }
//
//    /**
//     * 关闭串口
//     */
//    public void closeSerialPort() {// 关闭窗口
//        if (mSerialPort != null) {
//            mSerialPort.close();
//            mSerialPort = null;
//        }
//        try {
//            if(mInputStream!=null){
//                mInputStream.close();
//                mInputStream = null;
//            }
//            if(mOutputStream !=null){
//                mOutputStream.close();
//                mOutputStream.close();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * @author qiwenming
//     * @creation 2015-7-20 上午10:16:54
//     * @instruction 接受回调类
//     */
//    public interface ScanReciverListener {
//
//        /**
//         * 接受以后的处理方法
//         *
//         * @param string
//         */
//        public abstract void onReceived(String receviceStr);
//
//        /**
//         * 出错
//         *
//         * @param string
//         */
//        public abstract void onFail(String fialStr);
//
//        /**
//         * 出现异常
//         *
//         * @param e
//         */
//        public abstract void onErr(Exception e);
//
//    }
//
//    /**
//     * @author qiwenming
//     * @creation 2015-7-20 下午2:34:28
//     * @instruction 这个是我们用于存储读取的数据
//     */
//    public class RecevedData {
//        public ReturnType returnType;
//        /**
//         * 数据
//         */
//        public String receviedData;
//    }
//
//    /**
//     * @author qiwenming
//     * @creation 2015-7-20 下午2:36:21
//     * @instruction 使用辨识返回的数据的
//     */
//    public enum ReturnType {
//        ERR, // 错误
//        OK, // OK
//        Exception
//    }
//
//}