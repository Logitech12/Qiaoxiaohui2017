package com.bw.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 乔晓慧 on 2017/9/22.
 * 工具类:解析网址
 */

public class NetWork {
    //HttpURLConnection解析Json数据
    public String getStringJson(String jsonUrl){
        //声明
        URL url = null;
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        String str = "";
        try {
            //获取网址
            url = new URL(jsonUrl);
            //打开网址
            httpURLConnection = (HttpURLConnection) url.openConnection();
            //获取请求码
            int code = httpURLConnection.getResponseCode();
            //判断请求码
            if (code == 200){
                //解析
                inputStream = httpURLConnection.getInputStream();
                byte[] b = new byte[1024];
                int len = 0;
                //循环解析
                while ((len = inputStream.read(b)) != -1){
                    str += new String(b,0,len);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return str;
    }
}
