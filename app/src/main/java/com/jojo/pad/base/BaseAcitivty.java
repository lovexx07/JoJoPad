package com.jojo.pad.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.blankj.utilcode.util.EncodeUtils;
import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jojo.pad.app.MyApplication;
import com.jojo.pad.constant.HttpConstant;
import com.jojo.pad.listener.ResponseListener;
import com.jojo.pad.model.bean.result.ResTokenBean;
import com.jojo.pad.model.http.LzyResponse;
import com.jojo.pad.model.http.callback.JsonCallback;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.Map;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/12/27 0027.
 */

public abstract class BaseAcitivty extends AppCompatActivity{
    private Unbinder unbinder;
    protected Context mContext;
    protected Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScreenUtils.setLandscape(this);
        if (getLayoutId() != 0){
            setContentView(getLayoutId());
        }
        mContext = this;
        activity = this;
        unbinder = ButterKnife.bind(this);
        initView();
        setListener();
        initData();

    }

    public abstract int getLayoutId();
    public abstract void initView();
    public abstract void setListener();
    public abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);
        unbinder.unbind();
        unbinder = null;
    }

    public void toActivity(Class clazz){
        Intent intent = new Intent(this,clazz);
        startActivity(intent);
    }

    public void toActivity(Class clazz, Bundle bundle){
        Intent intent = new Intent(this,clazz);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void toActivityForResult(Class clazz, Bundle bundle,int requestCode){
        Intent intent = new Intent(this,clazz);
        if (bundle != null){
            intent.putExtras(bundle);
        }
        startActivityForResult(intent,requestCode);
    }

    public void toActivity(Class clazz, String name,String value){
        Intent intent = new Intent(this,clazz);
        intent.putExtra(name,value);
        startActivity(intent);
    }

    protected void showToast(String content){
        ToastUtils.showShort(content);
    }

    protected void showError(String error) {
        ToastUtils.showShort(error);
    }
    protected void showToast(int resid){
        ToastUtils.showShort(resid);
    }
}
