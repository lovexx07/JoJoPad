package com.jojo.pad.constant;

import com.jojo.pad.BuildConfig;

/**
 * Created by Administrator on 2017/12/27 0027.
 */

public interface Constant {
    /**
     * ViewClick 点击监听
     */
    //无码商品查找结果
    int VIEW_CLICK_TYPE_PRICE = 0;
    //主菜单返回接口
    int VIEW_CLICK_TYPE_MENU = 1;
    //搜索框返回
    int VIEW_CLICK_TYPE_SEARCH = 2;
    //商品查返回
    int VIEW_CLICK_TYPE_SEARCH_DIALOG = 3;
    //时间选择
    int VIEW_CLICK_TYPE_DATE_PICKER = 4;

    /**
     * 跳转 返回参数
     */
    int INTENT_FAR_RESULT_A = 110;
    boolean DEBUG = BuildConfig.DEBUG;
}
