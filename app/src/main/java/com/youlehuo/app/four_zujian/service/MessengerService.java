package com.youlehuo.app.four_zujian.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * Created by dayima on 17-1-10.
 * 使用Messenger绑定Service
 */

public class MessengerService extends Service {
    final Messenger messenger = new Messenger(new MyHandler());
    public static final int KKK = 1;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }

    class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            //处理发回的结果
            Messenger cmessenger = msg.replyTo;
            Message cmessage = Message.obtain(null,0,0,0);
            try {
                cmessenger.send(cmessage);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
