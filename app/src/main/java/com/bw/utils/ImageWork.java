package com.bw.utils;

import android.app.Application;

import com.bw.qiaoxiaohui20170922.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by 乔晓慧 on 2017/9/22.
 * 工具类:缓存图片
 */

public class ImageWork extends Application{
    DisplayImageOptions options;
    @Override
    public void onCreate() {
        super.onCreate();
        //创建ImageLoaderConfiguration
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .memoryCacheExtraOptions(200,400)
                .defaultDisplayImageOptions(options)
                .build();
        //加载图片的默认选项
        options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.mipmap.ic_launcher) // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.ic_launcher) // 设置图片加载或解码过程中发生错误显示的图片
                .build();
        //初始化ImageLoader
        ImageLoader.getInstance().init(config);
    }
}
