package com.jojo.pad.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/2 0002.
 */

public abstract class AbstractBaseAdapter <T> extends BaseAdapter {

    protected List<T> lists;
    protected Context context;

    public AbstractBaseAdapter(List<T> list ,Context context){
        lists = list == null ? new ArrayList<T>() : list;
        this.context = context;
    }

    public void delete(int position) {
        lists.remove(position);
        notifyDataSetChanged();
    }

    public List<T> getList() {
        return lists;
    }


    public void remove(T t) {
        if (lists.contains(t)) {
            lists.remove(t);
            notifyDataSetChanged();
        }
    }


    public void removeAll() {
        lists.clear();
        notifyDataSetChanged();
    }

    public void setNewData(List<T> data) {
        lists.clear();
        lists.addAll(data);
        notifyDataSetChanged();
    }
    public void addData(List<T> data){
        lists.addAll(data);
        notifyDataSetChanged();
    }
    public void add(T t) {
        lists.add(t);
        notifyDataSetChanged();
    }
    public void add(T t,int position) {
        lists.add(position,t);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return lists == null ? 0 : lists.size();
    }

    @Override
    public T getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent);
}
