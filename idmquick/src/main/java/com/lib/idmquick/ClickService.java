package com.lib.idmquick;

import android.app.IntentService;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Date;

import static com.lib.idmquick.MainActivity.HOUR;
import static com.lib.idmquick.MainActivity.MINUTE;

/**
 * Created by xiaohe on 17-12-28.
 */

public class ClickService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public ClickService() {
        super("ClickService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        int setHour = Integer.parseInt(intent.getStringExtra(HOUR));
        int setMinute = Integer.parseInt(intent.getStringExtra(MINUTE));
        while (true) {
            Date date = new Date();
            int hour = date.getHours();
            int minute = date.getMinutes();
            Log.e("time", "hour=" + hour + ",minute=" + minute);
            if (hour == setHour && minute >= setMinute) {
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
            SystemClock.sleep(178637);
        }
    }
}
