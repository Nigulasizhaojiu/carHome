package com.example.dllo.intentnet;

import android.app.Application;
import android.content.Context;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by dllo on 16/9/20.
 * 注意!!!写完Application类之后  一定要注册!!!
 */
public class MyApp extends Application{
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush
    }

    public static Context getContext() {
        return mContext;
    }
}
