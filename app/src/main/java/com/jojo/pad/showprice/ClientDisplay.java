//package com.jojo.pad.showprice;
//
//
//import java.io.IOException;
//import java.io.OutputStream;
//import java.util.HashMap;
//import java.util.Map;
//
//public class ClientDisplay {
//    /**
//     * 串行端口设置
//     */
//    public static final String PARAM_PORT_STR = "port";
//    /**
//     * 串口波特率设置
//     */
//    public static final String PARAM_BAUD_RATE_STR = "baudRate";
//    /**
//     * 顾客显示屏与串口对应的波特率设置位
//     */
//    public static final String PARAM_DISPLAY_RATE_STR = "displayRate";
//    /**
//     * 需要显示数值的参数
//     */
//    public static final String PARAM_DATA_STR = "data";
//    /**
//     * 状态灯参数
//     */
//    public static final String PARAM_STATE_STR = "state";
//    /**
//     * 状态灯全灭
//     */
//    public static final String DISPLAY_STATE_OFF = "0";
//    /**
//     * 单价状态灯
//     */
//    public static final String DISPLAY_STATE_PRICE = "1";
//    /**
//     * 总计状态灯
//     */
//    public static final String DISPLAY_STATE_TOTAL = "2        ";
//    /**
//     * 收款状态灯
//     */
//    public static final String DISPLAY_STATE_AMOUNT = "3";
//    /**
//     * 找零状态灯
//     */
//    public static final String DISPLAY_STATE_CHAGNE = "4";
//    /**
//     * 顾客显示屏显示的字符
//     */
//    public static final String PRINTABLE_STR = "0123456789.";
//
//
//
//    public static void main(String[] args) {
//        String port = "COM1";
//        String baudRate = null;
//        String displayRate = null;
//        String data = "1222222";
//        String state = "1111";
//        if (args != null) {
//            for (int i = 0; i < args.length; i++) {
//                if (args[i].startsWith("-p")) {
//                    port = args[i].substring(2);
//                } else if (args[i].startsWith("-br")) {
//                    baudRate = args[i].substring(2);
//                } else if (args[i].startsWith("-dr")) {
//                    displayRate = args[i].substring(2);
//                } else if (args[i].startsWith("-d")) {
//                    data = args[i].substring(2);
//                } else if (args[i].startsWith("-s")) {
//                    state = args[i].substring(2);
//                }
//            }
//        }
//        Map map = new HashMap();
//        map.put(PARAM_PORT_STR, port);
//        map.put(PARAM_BAUD_RATE_STR, baudRate);
//        map.put(PARAM_DISPLAY_RATE_STR, displayRate);
//        map.put(PARAM_DATA_STR, data);
//        map.put(PARAM_STATE_STR, state);
//        try {
//            sendDisplay(map);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    public static void sendDisplay (Map displayMap) throws Exception {
//                Object param1 = displayMap.get(PARAM_PORT_STR);
//                Object param2 = displayMap.get(PARAM_BAUD_RATE_STR);
//                Object param5 = displayMap.get(PARAM_DISPLAY_RATE_STR);
//                Object param3 = displayMap.get(PARAM_DATA_STR);
//                Object param4 = displayMap.get(PARAM_STATE_STR);
//                if (param1 == null || !(param1 instanceof String)) {
//                    throw new IllegalArgumentException("PARAM_PORT is not set value!");
//                }
//                String port = param1.toString();
//                int baudRate = 0;
//                String data = "";
//                String state = "";
//                String displayRate = "";
//                if (param2 != null) {
//                    try {
//                        baudRate = Integer.valueOf(param2.toString());
//                    } catch (Exception e) {
//                    }
//                }
//                if (param3 != null) {
//                    data = param3.toString();
//                }
//                if (param4 != null) {
//                    state = param4.toString();
//                }
//                if (param5 != null) {
//                    displayRate = param5.toString();
//                }
//                output(port, baudRate, displayRate, data, state);
//            }
//            /**
//             * 方法用途和描述:初始化顾客显示屏。
//             * 方法的实现逻辑描述：
//             * @param os 顾客显示屏输出流
//             * @throws Exception
//             * @author Tyler Chen 新增日期：2008-10-17
//             * @author Tyler Chen 修改日期：2008-10-17
//             */
//        public static void initDisplay (OutputStream os) throws Exception {
//            os.write(EpsonPosPrinterCommand.ESC_INIT);
//        }
//        /**
//         * 方法用途和描述:清空顾客显示屏
//         * 方法的实现逻辑描述：
//         * @param os 顾客显示屏输出流
//         * @throws Exception
//         * @author Tyler Chen 新增日期：2008-10-17
//         * @author Tyler Chen 修改日期：2008-10-17
//         */
//        public static void clearDisplay (OutputStream os) throws Exception {
//            os.write(EpsonPosPrinterCommand.CLR);
//        }
//        /**
//         * 方法用途和描述:向顾客显示屏输出需要显示的数值字符串
//         * 方法的实现逻辑描述：
//         * @param os 顾客显示屏输出流
//         * @param data 需要显示的数值字符串，只能显示8位：“0123456789.”。
//         * @throws Exception
//         * @author Tyler Chen 新增日期：2008-10-17
//         * @author Tyler Chen 修改日期：2008-10-17
//         */
//        public static void outputData (OutputStream os, String data)
//                        throws Exception {
//            if (data == null || data.length() == 0) {
//                return;
//            }
//            char[] chars = data.toCharArray();
//            boolean hasDot = false;
//            StringBuilder sb = new StringBuilder();
//            for (int i = 0; i - 1) {
//                sb.append(c);
//            }
//        }
//        if (hasDot && sb.length() > 9) {
//            sb.setLength(9);
//        } else if (sb.length() > 8) {
//            sb.setLength(8);
//        }
//        os.write(EpsonPosPrinterCommand.sendDisplayData(sb.toString()));
//    }
//        /**
//         * 方法用途和描述:设置“0 全暗”、“1 单价”、“2 总计”、“3 收款”、“4 找零”等显示。（具体设置需要看其规格文档。）
//         * 方法的实现逻辑描述：
//         * @param os 顾客显示屏输出流
//         * @param state “0 全暗”、“1 单价”、“2 总计”、“3 收款”、“4 找零”
//         * @throws Exception
//         * @author Tyler Chen 新增日期：2008-10-17
//         * @author Tyler Chen 修改日期：2008-10-17
//         */
//
//    public static void setDisplayStateLight(OutputStream os, String state)
//            throws Exception {
//        if (state.length() > 0) {
//            os.write(EpsonPosPrinterCommand.setDisplayState(state.charAt(0)));
//        }
//    }
//
//    /**
//     * 方法用途和描述:设定串行口波特率，默认：2400
//     * 方法的实现逻辑描述：
//     *
//     * @param os   顾客显示屏输出流
//     * @param rate 具体需要看顾客显示屏规格文档
//     * @throws Exception
//     * @author Tyler Chen 新增日期：2008-10-17
//     * @author Tyler Chen 修改日期：2008-10-17
//     */
//    public static void setDisplayBaudRate(OutputStream os, String rate)
//            throws Exception {
//        if (rate.length() > 0) {
//            os.write(EpsonPosPrinterCommand.setDisplayRate(rate.charAt(0)));
//        }
//    }
//
//    /**
//     * 方法用途和描述:打开顾客显示屏的串行端口，用定串行口后记得关闭打开的输入、输出流和串行口CommPort，否则端口将会被一直占用。
//     * 方法的实现逻辑描述：
//     *
//     * @param portName 端口名称，Windows：COM1，Linux：ttyS0
//     * @param rate     设定串行口的波特率，具体需要看顾客显示屏规格文档
//     * @return
//     * @throws Exception
//     * @author Tyler Chen 新增日期：2008-10-17
//     * @author Tyler Chen 修改日期：2008-10-17
//     */
//    public static CommPort openConnection(String portName, int rate)
//            throws Exception {
//        CommPortIdentifier port = null;
//        CommPort open = null;
//        port = CommPortIdentifier.getPortIdentifier(portName);
//        open = port.open(portName, 100);
//        System.out.println(open.getClass().getName());
//        if (open instanceof RXTXPort) {
//            RXTXPort rxtx = (RXTXPort) open;
//            rxtx.setSerialPortParams(getBaudRate(rate), SerialPort.DATABITS_8,
//                    SerialPort.STOPBITS_1,
//                    SerialPort.PARITY_NONE);
//            System.out.println("baud rate:" + rxtx.getBaudRate());
//        } else {
//            throw new IOException(portName + " is not a CommPort port!");
//        }
//        return open;
//    }
//
//    /**
//     * 方法用途和描述:向顾客显示屏输出显示数据
//     * 方法的实现逻辑描述：
//     *
//     * @param portName    :端口名称，Windows：COM1，Linux：ttyS0
//     * @param rate        :串行口波特率，默认：2400
//     * @param displayRate :串行口波特率需要与顾客显示屏的波特设置对应，默认：0。
//     *                    （如一些顾显波特率为“2400”对应设置位为“0”，“4800”对应设置位为“1”，“9600”对应设置位为“2”，具体设置需要看其规格文档。）
//     * @param data        :需要显示的数值字符串，只能显示8位：“0123456789.”。
//     * @param state       :设置“0 全暗”、“1 单价”、“2 总计”、“3 收款”、“4 找零”等显示。（具体设置需要看其规格文档。）
//     * @throws Exception
//     * @author Tyler Chen 新增日期：2008-10-17
//     * @author Tyler Chen 修改日期：2008-10-17
//     */
//    public static void output(String portName, int rate, String displayRate,
//                              String data, String state) throws Exception {
//        CommPort open = null;
//        try {
//            open = openConnection(portName, rate);
//            OutputStream os = open.getOutputStream();
//            setDisplayBaudRate(os, displayRate);
//            initDisplay(os);
//            clearDisplay(os);
//            outputData(os, data);
//            setDisplayStateLight(os, state);
//            os.flush();
//            os.close();
//        } catch (Exception e) {
//            System.out.println(e.getMessage() + "====\n\n\n\n====");
//            e.printStackTrace();
//            if (e instanceof IOException
//                    && e.getMessage()
//                    .startsWith("Resource temporarily unavailable")) {
//            } else {
//                throw e;
//            }
//        } finally {
//            if (open != null) {
//                open.close();
//            }
//        }
//    }
//
//    private static int getBaudRate(int rate) {
//        switch (rate) {
//            case 9600:
//                return 9600;
//            case 4800:
//                return 4800;
//            case 2400:
//                return 2400;
//            case 1200:
//                return 1200;
//            case 600:
//                return 600;
//            case 300:
//                return 300;
//            default:
//                return 2400;
//        }
//    }
//
//    private static char getRateByte(int rate) {
//        char rateByte = '2';
//        switch (rate) {
//            case 9600:
//                rateByte = '0';
//                break;
//            case 4800:
//                rateByte = '1';
//                break;
//            case 2400:
//                rateByte = '2';
//                break;
//            case 1200:
//                rateByte = '3';
//                break;
//            case 600:
//                rateByte = '4';
//                break;
//            case 300:
//                rateByte = '5';
//                break;
//            default:
//                break;
//        }
//        return rateByte;
//    }
//}