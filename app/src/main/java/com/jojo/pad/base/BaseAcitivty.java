package com.jojo.pad.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jojo.pad.util.ToastUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/12/27 0027.
 */

public abstract class BaseAcitivty extends AppCompatActivity{
    private Unbinder unbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutId() != 0){
            setContentView(getLayoutId());
        }
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
        intent.putExtras(bundle);
        startActivityForResult(intent,requestCode);
    }
    public void toActivity(Class clazz, String name,String value){
        Intent intent = new Intent(this,clazz);
        intent.putExtra(name,value);
        startActivity(intent);
    }

    protected void showToast(String content){
        ToastUtil.show(content);
    }

    protected void showError(String error) {
        ToastUtil.show(error);
    }
    protected void showToast(int resid){
        ToastUtil.show(resid);
    }
}
