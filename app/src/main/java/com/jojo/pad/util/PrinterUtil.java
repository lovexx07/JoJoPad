package com.jojo.pad.util;

import com.blankj.utilcode.util.TimeUtils;
import com.jojo.pad.model.bean.OrderBean;
import com.jojo.pad.model.bean.print.PrintGoodBean;
import com.jojo.pad.model.bean.print.PrintRecharge;

import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2018/1/12 0012.
 */

public class PrinterUtil {

    public static byte[] printSaleOrder(PrintGoodBean printGoodBean) {
        try {
            byte[] init_printer = printerCmdUtils.init_printer();
            byte[] next2Line = printerCmdUtils.nextLine(2);
            byte[] next4Line = printerCmdUtils.nextLine(4);
            byte[] fontSize2Small = printerCmdUtils.fontSizeSetSmall(3);

            byte[] center = printerCmdUtils.alignCenter();


            byte[] left = printerCmdUtils.alignLeft();
            byte[] right = printerCmdUtils.alignRight();


            byte[] title = AccountUtil.store_name.getBytes("gb2312");

            byte[] breakPartial = printerCmdUtils.feedPaperCutAll();


            byte[] orderSerinum = ("单号："+printGoodBean.getOrder_id()).getBytes("gb2312");
            byte[] currentTime = ("时间："+ TimeUtils.getNowString()).getBytes("gb2312");

            byte[] ordertitle = ("商品名称             单价   数量").getBytes("gb2312");

            String goodstr ="";

            for (OrderBean goodBean :printGoodBean.getDatas()){
                goodstr +=goodBean.getGoods_name()+"       "+goodBean.getGoods_price()+"   "+goodBean.getCount()+"\n";


            }
            byte[] goodsbyte = goodstr.getBytes("gb2312");
            byte[] orderSum = ("原价: "+printGoodBean.getSum()).getBytes("gb2312");
            byte[] orderCount = ("总数: "+printGoodBean.getCount()).getBytes("gb2312");

            byte[] orderEnd = ("实收: ￥"+printGoodBean.getReal_money()).getBytes("gb2312");
            byte[] orderpay = ("支付: "+printGoodBean.getPay_type_name()).getBytes("gb2312");


            byte[] nextLine = printerCmdUtils.nextLine(1);
            byte[] tips = "欢迎光临！".getBytes("gb2312");
            byte[] line = "-------------------------------".getBytes("gb2312");


            byte[][] cmdBytes = {
                    init_printer,
                    center,title, nextLine,
                    left,fontSize2Small, orderSerinum,nextLine,
                    currentTime,nextLine,
                    line,nextLine,
                    ordertitle,nextLine,
                    goodsbyte,nextLine,
                    line,nextLine,
                    orderSum,
                    right,orderCount,nextLine,
                    left,orderEnd,
                    right,orderpay,nextLine,
                    left,
                    line,nextLine,
                    center,tips,
                    next4Line,
                    breakPartial
            };
            return printerCmdUtils.byteMerger(cmdBytes);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] printRecharge(PrintRecharge printRecharge) {
        try {
            byte[] init_printer = printerCmdUtils.init_printer();
            byte[] next2Line = printerCmdUtils.nextLine(2);
            byte[] next4Line = printerCmdUtils.nextLine(4);

            byte[] center = printerCmdUtils.alignCenter();


            byte[] left = printerCmdUtils.alignLeft();

            byte[] title = "------------会员充值-----------".getBytes("gb2312");
            byte[] currentTime = ("时间："+ TimeUtils.getNowString()).getBytes("gb2312");
            byte[] breakPartial = printerCmdUtils.feedPaperCutAll();


            byte[] number = ("卡号："+printRecharge.getNumber()).getBytes("gb2312");
            byte[] recharge_fee = ("充值前金额："+printRecharge.getRecharge_fee()).getBytes("gb2312");
            byte[] recharge = ("充值金额："+printRecharge.getRecharge()).getBytes("gb2312");
            byte[] send = ("赠送："+printRecharge.getSend()).getBytes("gb2312");
            byte[] recharge_end = ("增送金额："+printRecharge.getRecharge_end()).getBytes("gb2312");



            byte[] nextLine = printerCmdUtils.nextLine(1);
            byte[] tips = "欢迎光临！".getBytes("gb2312");
            byte[] line = "-------------------------------".getBytes("gb2312");


            byte[][] cmdBytes = {
                    init_printer,
                    center,title, next2Line,left,
                    number,nextLine,
                    recharge_fee,nextLine,
                    recharge,nextLine,
                    send,nextLine,
                    recharge_end,nextLine,
                    currentTime,nextLine,
                    line,
                    center,tips,
                    next4Line,
                    breakPartial
            };
            return printerCmdUtils.byteMerger(cmdBytes);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
