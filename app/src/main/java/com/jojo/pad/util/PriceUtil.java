package com.jojo.pad.util;

/**
 * Created by ruifeng on 2018/1/12.
 */

public class PriceUtil {
    /**
     * 价格 数量 获取总价值
     * @param pricestr
     * @param count
     * @return
     */
    public static String getSumMoney(String pricestr,int count){
        try {
            int priceInt = Integer.parseInt(pricestr);
            int  sum = priceInt * count ;
            return sum+"";
        }catch (NumberFormatException e){
            double price = Double.parseDouble(pricestr);
            double sum = price * count ;
            return sum+"";
        }
    }

}
