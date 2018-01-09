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
        String token ="Public/getToken";
        String signIn ="Member/signIn";
    }
}
