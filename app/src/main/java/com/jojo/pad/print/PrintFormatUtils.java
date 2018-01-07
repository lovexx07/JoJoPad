//package com.jojo.pad.print;
//
///**
// * Created by Administrator on 2018/1/7 0007.
// */
//
//import java.io.UnsupportedEncodingException;
//
///**
// * 打印格式
// * Created by john on 17-3-23.
// */
//
//public class PrintFormatUtils {
//    // 对齐方式
//    public static final int ALIGN_LEFT = 0;   // 靠左
//    public static final int ALIGN_CENTER = 1;  // 居中
//    public static final int ALIGN_RIGHT = 2;  // 靠右
//
//    //字体大小
//    public static final int FONT_NORMAL = 0;  // 正常
//    public static final int FONT_MIDDLE = 1;  // 中等
//    public static final int FONT_BIG = 2;    // 大
//
//    //加粗模式
//    public static final int FONT_BOLD = 0;       // 字体加粗
//    public static final int FONT_BOLD_CANCEL = 1;    // 取消加粗
//
//    /**
//     * 打印二维码
//     * @param qrCode
//     * @return
//     */
//    public static String getQrCodeCmd(String qrCode) {
//        byte[] data;
//        int store_len = qrCode.length() + 3;
//        byte store_pL = (byte) (store_len % 256);
//        byte store_pH = (byte) (store_len / 256);
//
//        // QR Code: Select the model
//        //       Hex   1D   28   6B   04   00   31   41   n1(x32)   n2(x00) - size of model
//        // set n1 [49 x31, model 1] [50 x32, model 2] [51 x33, micro qr code]
//        // https://reference.epson-biz.com/modules/ref_escpos/index.php?content_id=140
//        byte[] modelQR = {(byte)0x1d, (byte)0x28, (byte)0x6b, (byte)0x04, (byte)0x00, (byte)0x31, (byte)0x41, (byte)0x32, (byte)0x00};
//
//        // QR Code: Set the size of module
//        // Hex   1D   28   6B   03   00   31   43   n
//        // n depends on the printer
//        // https://reference.epson-biz.com/modules/ref_escpos/index.php?content_id=141
//        byte[] sizeQR = {(byte)0x1d, (byte)0x28, (byte)0x6b, (byte)0x03, (byte)0x00, (byte)0x31, (byte)0x43, (byte)0x08};
//
//        //     Hex   1D   28   6B   03   00   31   45   n
//        // Set n for error correction [48 x30 -> 7%] [49 x31-> 15%] [50 x32 -> 25%] [51 x33 -> 30%]
//        // https://reference.epson-biz.com/modules/ref_escpos/index.php?content_id=142
//        byte[] errorQR = {(byte)0x1d, (byte)0x28, (byte)0x6b, (byte)0x03, (byte)0x00, (byte)0x31, (byte)0x45, (byte)0x31};
//
//        // QR Code: Store the data in the symbol storage area
//        // Hex   1D   28   6B   pL   pH   31   50   30   d1...dk
//        // https://reference.epson-biz.com/modules/ref_escpos/index.php?content_id=143
//        //            1D     28     6B     pL     pH cn(49->x31) fn(80->x50) m(48->x30) d1…dk
//        byte[] storeQR = {(byte)0x1d, (byte)0x28, (byte)0x6b, store_pL, store_pH, (byte)0x31, (byte)0x50, (byte)0x30};
//
//        // QR Code: Print the symbol data in the symbol storage area
//        // Hex   1D   28   6B   03   00   31   51   m
//        // https://reference.epson-biz.com/modules/ref_escpos/index.php?content_id=144
//        byte[] printQR = {(byte)0x1d, (byte)0x28, (byte)0x6b, (byte)0x03, (byte)0x00, (byte)0x31, (byte)0x51, (byte)0x30};
//
//        data = byteMerger(modelQR, sizeQR);
//        data = byteMerger(data, errorQR);
//        data = byteMerger(data, storeQR);
//        data = byteMerger(data, qrCode.getBytes());
//        data = byteMerger(data, printQR);
//
//        return new String(data);
//    }
//
//    /**
//     * 打印条码
//     * @param barcode
//     * @return
//     */
//    public static String getBarcodeCmd(String barcode) {
//        // 打印 Code-128 条码时需要使用字符集前缀
//        // "{A" 表示大写字母
//        // "{B" 表示所有字母，数字，符号
//        // "{C" 表示数字，可以表示 00 - 99 的范围
//
//
//        byte[] data;
//
//        String btEncode;
//
//        if (barcode.length() < 18) {
//            // 字符长度小于15的时候直接输出字符串
//            btEncode = "{B" + barcode;
//        } else {
//            // 否则做一点优化
//
//            int startPos = 0;
//            btEncode = "{B";
//
//            for (int i = 0; i < barcode.length(); i++) {
//                char curChar = barcode.charAt(i);
//
//                if (curChar < 48 || curChar > 57 || i == (barcode.length() - 1)) {
//                    // 如果是非数字或者是最后一个字符
//
//                    if (i - startPos >= 10) {
//                        if (startPos == 0) {
//                            btEncode = "";
//                        }
//
//                        btEncode += "{C";
//
//                        boolean isFirst = true;
//                        int numCode = 0;
//
//                        for (int j = startPos; j < i; j++) {
//
//                            if (isFirst) { // 处理第一位
//                                numCode = (barcode.charAt(j) - 48) * 10;
//                                isFirst = false;
//                            } else { // 处理第二位
//                                numCode += (barcode.charAt(j) - 48);
//                                btEncode += (char) numCode;
//                                isFirst = true;
//                            }
//
//                        }
//
//                        btEncode += "{B";
//
//                        if (!isFirst) {
//                            startPos = i - 1;
//                        } else {
//                            startPos = i;
//                        }
//                    }
//
//                    for (int k = startPos; k <= i; k++) {
//                        btEncode += barcode.charAt(k);
//                    }
//                    startPos = i + 1;
//                }
//
//            }
//        }
//
//
//        // 设置 HRI 的位置，02 表示下方
//        byte[] hriPosition = {(byte) 0x1d, (byte) 0x48, (byte) 0x02};
//        // 最后一个参数表示宽度 取值范围 1-6 如果条码超长则无法打印
//        byte[] width = {(byte) 0x1d, (byte) 0x77, (byte) 0x02};
//        byte[] height = {(byte) 0x1d, (byte) 0x68, (byte) 0xfe};
//        // 最后两个参数 73 ： CODE 128 || 编码的长度
//        byte[] barcodeType = {(byte) 0x1d, (byte) 0x6b, (byte) 73, (byte) btEncode.length()};
//        byte[] print = {(byte) 10, (byte) 0};
//
//        data = PrintFormatUtils.byteMerger(hriPosition, width);
//        data = PrintFormatUtils.byteMerger(data, height);
//        data = PrintFormatUtils.byteMerger(data, barcodeType);
//        data = PrintFormatUtils.byteMerger(data, btEncode.getBytes());
//        data = PrintFormatUtils.byteMerger(data, print);
//
//        return new String(data);
//    }
//
//    /**
//     * 切纸
//     * @return
//     */
//    public static String getCutPaperCmd() {
//        // 走纸并切纸，最后一个参数控制走纸的长度
//        byte[] data = {(byte) 0x1d, (byte) 0x56, (byte) 0x42, (byte) 0x15};
//
//        return new String(data);
//    }
//
//    /**
//     * 对齐方式
//     * @param alignMode
//     * @return
//     */
//    public static String getAlignCmd(int alignMode) {
//        byte[] data = {(byte) 0x1b, (byte) 0x61, (byte) 0x0};
//        if (alignMode == ALIGN_LEFT) {
//            data[2] = (byte) 0x00;
//        } else if (alignMode == ALIGN_CENTER) {
//            data[2] = (byte) 0x01;
//        } else if (alignMode == ALIGN_RIGHT) {
//            data[2] = (byte) 0x02;
//        }
//
//        return new String(data);
//    }
//
//    /**
//     * 字体大小
//     * @param fontSize
//     * @return
//     */
//    public static String getFontSizeCmd(int fontSize) {
//        byte[] data = {(byte) 0x1d, (byte) 0x21, (byte) 0x0};
//        if (fontSize == FONT_NORMAL) {
//            data[2] = (byte) 0x00;
//        } else if (fontSize == FONT_MIDDLE) {
//            data[2] = (byte) 0x01;
//        } else if (fontSize == FONT_BIG) {
//            data[2] = (byte) 0x11;
//        }
//
//        return new String(data);
//    }
//
//    /**
//     * 加粗模式
//     * @param fontBold
//     * @return
//     */
//    public static String getFontBoldCmd(int fontBold) {
//        byte[] data = {(byte) 0x1b, (byte) 0x45, (byte) 0x0};
//
//        if (fontBold == FONT_BOLD) {
//            data[2] = (byte) 0x01;
//        } else if (fontBold == FONT_BOLD_CANCEL) {
//            data[2] = (byte) 0x00;
//        }
//
//        return new String(data);
//    }
//
//    /**
//     * 打开钱箱
//     * @return
//     */
//    public static String getOpenDrawerCmd() {
//        byte[] data = new byte[4];
//        data[0] = 0x10;
//        data[1] = 0x14;
//        data[2] = 0x00;
//        data[3] = 0x00;
//
//        return new String(data);
//    }
//
//    /**
//     * 字符串转字节数组
//     * @param str
//     * @return
//     */
//    public static byte[] stringToBytes(String str) {
//        byte[] data = null;
//
//        try {
//            byte[] strBytes = str.getBytes("utf-8");
//
//            data = (new String(strBytes, "utf-8")).getBytes("gbk");
//        } catch (UnsupportedEncodingException exception) {
//            exception.printStackTrace();
//        }
//
//        return data;
//    }
//
//    /**
//     * 字节数组合并
//     * @param bytesA
//     * @param bytesB
//     * @return
//     */
//    public static byte[] byteMerger(byte[] bytesA, byte[] bytesB) {
//        byte[] bytes = new byte[bytesA.length + bytesB.length];
//        System.arraycopy(bytesA, 0, bytes, 0, bytesA.length);
//        System.arraycopy(bytesB, 0, bytes, bytesA.length, bytesB.length);
//        return bytes;
//    }
//}