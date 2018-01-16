//package com.jojo.pad.util;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class ClientDisplay {
//
//    /** 串行端口设置 */
//    public static final String PARAM_PORT_STR = "port";
//    /** 串口波特率设置 */
//    public static final String PARAM_BAUD_RATE_STR = "baudRate";
//    /** 顾客显示屏与串口对应的波特率设置位 */
//    public static final String PARAM_DISPLAY_RATE_STR = "displayRate";
//    /** 需要显示数值的参数 */
//    public static final String PARAM_DATA_STR = "data";
//    /** 状态灯参数 */
//    public static final String PARAM_STATE_STR = "state";
//    /** 状态灯全灭 */
//    public static final String DISPLAY_STATE_OFF = "0";
//    /** 单价状态灯 */
//    public static final String DISPLAY_STATE_PRICE = "1";
//    /** 总计状态灯 */
//    public static final String DISPLAY_STATE_TOTAL = "2 ";
//    /** 收款状态灯 */
//    public static final String DISPLAY_STATE_AMOUNT = "3";
//    /** 找零状态灯 */
//    public static final String DISPLAY_STATE_CHAGNE = "4";
//    /** 顾客显示屏显示的字符 */
//    public static final String PRINTABLE_STR = "0123456789.";
//
//    //public static final String
//
//    public static void main(String[] args) {
//        String port = "COM1";
//        String baudRate = null;
//        String displayRate = null;
//        String data = "1222222";
//        String state = "1111";
//        if (args != null) {
//            for (int i = 0; i
//            if (args.startsWith("-p")) {
//                port = args.substring(2);
//            } else if (args.startsWith("-br")) {
//                baudRate = args.substring(2);
//            } else if (args.startsWith("-dr")) {
//                displayRate = args.substring(2);
//            } else if (args.startsWith("-d")) {
//                data = args.substring(2);
//            } else if (args.startsWith("-s")) {
//                state = args.substring(2);
//            }
//        }
//    }
//    Map map = new HashMap();
//        map.put(PARAM_PORT_STR, port);
//        map.put(PARAM_BAUD_RATE_STR, baudRate);
//        map.put(PARAM_DISPLAY_RATE_STR, displayRate);
//        map.put(PARAM_DATA_STR, data);
//        map.put(PARAM_STATE_STR, state);
//        try {
//        sendDisplay(map);
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
//}
//
