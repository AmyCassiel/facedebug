package com.jiachang.facedebug;

import android.app.Application;

import com.tencent.bugly.Bugly;

/**
 * @author Mickey.Ma
 * @date 2020-01-06
 * @description
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Bugly.init(getApplicationContext(), "7a84b5a102", false);
    }
}
