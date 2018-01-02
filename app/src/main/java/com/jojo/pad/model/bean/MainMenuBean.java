package com.jojo.pad.model.bean;

/**
 * Created by Administrator on 2018/1/2 0002.
 */

public class MainMenuBean {
    private int resid;
    private String title;
    private int type;

    public MainMenuBean() {
    }

    public MainMenuBean(int resid, String title, int type) {
        this.resid = resid;
        this.title = title;
        this.type = type;
    }

    public int getResid() {
        return resid;
    }

    public void setResid(int resid) {
        this.resid = resid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
