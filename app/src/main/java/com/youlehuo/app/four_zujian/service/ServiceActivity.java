package com.youlehuo.app.four_zujian.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.youlehuo.app.BaseActivity;
import com.youlehuo.app.R;

import butterknife.BindView;

/**
 * Created by dayima on 17-1-10.
 */

public class ServiceActivity extends BaseActivity implements ServiceConnection{
    @BindView(R.id.but_service1)
    Button but_service1;
    @BindView(R.id.but_service2)
    Button but_service2;
    @BindView(R.id.but_service3)
    Button but_service3;
    @BindView(R.id.but_service4)
    Button but_service4;
    final Messenger cmessenger = new Messenger(new ServiceActivity.Chandler());
    Messenger smessenger;
    @Override
    protected int setView() {
        return R.layout.serviceactivity;
    }

    @Override
    protected void initView() {
        but_service1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context,MyService.class);
                startService(intent);
            }
        });
        but_service2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context,MyService.class);
                bindService(intent,ServiceActivity.this, Context.BIND_AUTO_CREATE);
            }
        });
        but_service3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message = Message.obtain(null,MessengerService.KKK,0,0);
                try {
                    message.replyTo = cmessenger;
                    smessenger.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        but_service4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            smessenger = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    @Override
    protected void initVariables() {
        bindService(new Intent(context,MessengerService.class),connection,Context.BIND_AUTO_CREATE);
    }
    class Chandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            //处理发回的结果
            super.handleMessage(msg);
        }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        Log.e("Service",name.toString()+service.toString());
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        Log.e("Service",name.toString());
    }

    @Override
    protected void onDestroy() {
        unbindService(connection);
//        unbindService(this);
        super.onDestroy();
    }
}
