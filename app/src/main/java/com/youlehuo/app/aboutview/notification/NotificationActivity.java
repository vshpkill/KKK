package com.youlehuo.app.aboutview.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.graphics.BitmapFactory;
import android.support.v7.app.NotificationCompat;

import com.youlehuo.app.BaseActivity;
import com.youlehuo.app.R;

/**
 * Created by dayima on 17-1-5.
 */

public class NotificationActivity extends BaseActivity {
    @Override
    protected int setView() {
        return R.layout.notificationactivity;
    }

    @Override
    protected void initView() {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        Notification notification = builder
                .setContentTitle("这是通知标题")
                .setContentText("这是通知内容")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.doctors_icon_question)
                .setDefaults(Notification.DEFAULT_ALL)
                .setColor(0xFFFD671A)
                .setLargeIcon(BitmapFactory.decodeResource(
                        getResources(), R.mipmap.ic_launcher))
                .build();
        manager.notify(1, notification);
    }

    @Override
    protected void initVariables() {

    }
}
