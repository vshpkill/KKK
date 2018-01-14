package com.youlehuo.app.four_zujian.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.util.Date;

/**
 * Created by xiaohe on 17-12-28.
 */

public class ClickService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public ClickService() {
        super("ClickService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        while (true) {
            Date date = new Date();
            int hour = date.getHours();
            int minute = date.getMinutes();
            Log.e("time","hour="+hour+",minute="+minute);
            if (hour == 18&&minute>47) {
                /**包管理器*/
                PackageManager packageManager = getPackageManager();
                /**获得Intent*/
                intent = packageManager.getLaunchIntentForPackage("com.client.xrxs.com.xrxsapp");
                if (intent != null) {
                    startActivity(intent);
                }
                break;
            } else {

            }
            SystemClock.sleep(20000);
        }

//        int i = 0;
//        SystemClock.sleep(10000);
//        while (i < 20) {
////            execShell(amd);
//            execShell("sendevent /dev/input/event1 1 158 1");
//            execShell("sendevent /dev/input/event1 1 158 0");
//            SystemClock.sleep(5000);
//            i ++;
//        }
//        int i = 0;
//        SystemClock.sleep(10000);
//        while (i<20){
//            Instrumentation mInst = new Instrumentation();
//            mInst.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(),
//                    SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, 100, 200, 0));    //x,y 即是事件的坐标
//            SystemClock.sleep(100);
//            mInst.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(),
//                    SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, 100, 200, 0));
//            SystemClock.sleep(1000);
//        }
    }

    private String amd = "input tap 200 200";

    public void execShell(String cmd) {
        try {
            Process process = Runtime.getRuntime().exec("sh");
            OutputStream outputStream = process.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(
                    outputStream);
            dataOutputStream.writeBytes(cmd + "\n");
            dataOutputStream.flush();
            dataOutputStream.close();
            outputStream.close();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
