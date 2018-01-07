//package com.jojo.pad.print;
//
///**
// * Created by Administrator on 2018/1/7 0007.
// */
//
//public class PrintContract {
//    /**
//     * 打印内容
//     */
//    public static StringBuilder createXxTxt(String printstr) {
//
//        StringBuilder builder = new StringBuilder();
//
//        //设置大号字体以及加粗
//        builder.append(PrintFormatUtils.getFontSizeCmd(PrintFormatUtils.FONT_BIG));
//        builder.append(PrintFormatUtils.getFontBoldCmd(PrintFormatUtils.FONT_BOLD));
//
//        // 标题
//        builder.append("Title");
//        //换行，调用次数根据换行数来控制
//        addLineSeparator(builder);
//
//        //设置普通字体大小、不加粗
//        builder.append(PrintFormatUtils.getFontSizeCmd(PrintFormatUtils.FONT_NORMAL));
//        builder.append(PrintFormatUtils.getFontBoldCmd(PrintFormatUtils.FONT_BOLD_CANCEL));
//
//        //内容
//        int x = printstr.length();
//
//        //设置某两列文字间空格数, x需要计算出来
//        addIdenticalStrToStringBuilder(builder, x, " ");
//
//        //切纸
//        builder.append(PrintFormatUtils.getCutPaperCmd());
//
//        return builder;
//    }
//
//    /**
//     * 向StringBuilder中添加指定数量的相同字符
//     *
//     * @param printCount  添加的字符数量
//     * @param identicalStr 添加的字符
//     */
//
//    private static void addIdenticalStrToStringBuilder(StringBuilder builder, int printCount, String identicalStr) {
//        for (int i = 0; i < printCount; i++) {
//            builder.append(identicalStr);
//        }
//    }
//
//    /**
//     * 根据字符串截取前指定字节数,按照GBK编码进行截取
//     *
//     * @param str 原字符串
//     * @param len 截取的字节数
//     * @return 截取后的字符串
//     */
//    private static String subStringByGBK(String str, int len) {
//        String result = null;
//        if (str != null) {
//            try {
//                byte[] a = str.getBytes("GBK");
//                if (a.length <= len) {
//                    result = str;
//                } else if (len > 0) {
//                    result = new String(a, 0, len, "GBK");
//                    int length = result.length();
//                    if (str.charAt(length - 1) != result.charAt(length - 1)) {
//                        if (length < 2) {
//                            result = null;
//                        } else {
//                            result = result.substring(0, length - 1);
//                        }
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return result;
//    }
//
//    /**
//     * 添加换行符
//     */
//    private static void addLineSeparator(StringBuilder builder) {
//        builder.append("\n");
//    }
//
//    /**
//     * 在GBK编码下，获取其字符串占据的字符个数
//     */
//    private static int getCharCountByGBKEncoding(String text) {
//        try {
//            return text.getBytes("GBK").length;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return 0;
//        }
//    }
//
//
//    /**
//     * 打印相关配置
//     */
//    public static class PrintConfig {
//        public int maxLength = 30;
//
//        public boolean printBarcode = false; // 打印条码
//        public boolean printQrCode = false;  // 打印二维码
//        public boolean printEndText = true;  // 打印结束语
//        public boolean needCutPaper = false; // 是否切纸
//    }
//
//}