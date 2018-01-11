package com.jojo.pad.constant;

/**
 * Created by ruifeng on 2018/1/9.
 */

public class HttpConstant {
    public static  final  String appid = "sbVkKkbxwrVIFLr30c";
    public static  final  String secret = "eDIor4hR";
    public static final String BaseApi = "https://183.62.12.10:8443/haoappAdmin/";

    public static String TOKEN = "";
    public static Long EXPIRED_TIME ;


    public interface Api{
        String token ="Public/getToken";//获取token值
        String signIn ="Member/signIn";//登录
        String searchMember ="Customer/getList";//查找会员列表
        String addMember ="Customer/add";//增加会员
        String getMemberNember ="Customer/getMaxNumber";//获取会员卡号
        String memberGetInfo ="Customer/getInfo";//获取会员详细信息
        String memberGetRecharge ="Customer/getRecharge";//获取会员储值信息

        String rechargeAdd ="Recharge/add";//会员充值
    }
}
