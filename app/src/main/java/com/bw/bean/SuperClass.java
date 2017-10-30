package com.bw.bean;

import java.util.List;

/**
 * Created by 乔晓慧 on 2017/9/22.
 */

public class SuperClass {
    //属性
    private int code;
    private String msg;
    private List<Newslist> newslist;

    //get set方法
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Newslist> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<Newslist> newslist) {
        this.newslist = newslist;
    }
}
