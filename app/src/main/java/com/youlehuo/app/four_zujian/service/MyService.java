package com.youlehuo.app.four_zujian.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by dayima on 17-1-10.
 * 继承Binder类
 */

public class MyService extends Service {
    private final IBinder mBinder = new MyBinder();
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    public void stringToInteger(String value){
        Log.e("service",value);
        Integer.valueOf(value);
    }
    /**
     * onStartCommand()返回一个整形变量，该变量必须是下列常量之一：
     *
     * START_NOT_STICKY：若执行完onStartCommand()方法后，系统就kill了service，
     * 不要再重新创建service，除非系统回传了一个pending intent。
     * 这避免了在不必要的时候运行service，您的应用也可以restart任何未完成的操作。
     *
     * START_STICKY：若系统在onStartCommand()执行并返回后kill了service，
     * 那么service会被recreate并回调onStartCommand()。
     * dangerous不要重新传递最后一个Intent（do not redeliver the last intent）。
     * 相反，系统回调onStartCommand()时回传一个空的Intent，除非有 pending intents传递，
     * 否则Intent将为null。该模式适合做一些类似播放音乐的操作。
     *
     * START_REDELIVER_INTENT：若系统在onStartCommand()执行并返回后kill了service，
     * 那么service会被recreate并回调onStartCommand()并将最后一个Intent回传至该方法。
     * 任何 pending intents都会被轮流传递。该模式适合做一些类似下载文件的操作。
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    class MyBinder extends Binder{
        public Service getService(){
            return MyService.this;
        }
    }
}
