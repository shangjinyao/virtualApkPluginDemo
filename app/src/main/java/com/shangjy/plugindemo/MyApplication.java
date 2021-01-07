package com.shangjy.plugindemo;

import android.app.Application;
import android.content.Context;

import com.didi.virtualapk.PluginManager;

/**
 * @Description: java类作用描述
 * @Author: shangjy
 * @CreateDate: 2021/1/7 15:33
 * @Version: 1.0
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        PluginManager.getInstance(base).init();
    }
}
