package com.youlehuo.app.four_zujian.handlermessage;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.youlehuo.app.BaseActivity;
import com.youlehuo.app.R;

import butterknife.BindView;

/**
 * Created by dayima on 16-12-27.
 */

public class MessageActivity extends BaseActivity {
    @BindView(R.id.but_send)
    Button but_send;
    private Handler handler;

    @Override
    protected void initView() {

    }

    @Override
    protected void initVariables() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                handler = new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        Log.e("示例提醒","这是一个子线程handler");
                    }
                };
                Looper.loop();
            }
        }).start();
        but_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.sendEmptyMessage(0);
            }
        });
    }

    @Override
    protected int setView() {
        return R.layout.messageactivity;
    }
}
