package com.youlehuo.app.four_zujian.intentaction;

import android.app.PendingIntent;
import android.content.Intent;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;

import com.youlehuo.app.BaseActivity;
import com.youlehuo.app.R;
import com.youlehuo.app.aboutview.pobupwindow.bubbleEngine.HelpPopupManager;

import butterknife.BindView;

/**
 * Created by dayima on 17-1-9.
 */

public class OpenIntentActivity extends BaseActivity {
    @BindView(R.id.but_open)
    Button but_open;
    @BindView(R.id.but_pending)
    Button but_pending;
    @BindView(R.id.but_open1)
    Button but_open1;
    @BindView(R.id.but_open2)
    Button but_open2;
    @BindView(R.id.but_open3)
    Button but_open3;
    @Override
    protected int setView() {
        return R.layout.openintentactivity;
    }

    @Override
    protected void initView() {
        HelpPopupManager.addHelpPopupWindow(1, 3, but_open, "This is radio button test1");
        HelpPopupManager.addHelpPopupWindow(1, 3, but_pending, "This is radio button test2");
        HelpPopupManager.addHelpPopupWindow(1, 3, but_open1, "This is radio button test3");
        but_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "textMessage");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });
        but_pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setClass(context,IntentActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(context,110,sendIntent,PendingIntent.FLAG_UPDATE_CURRENT);
                try {
                    pendingIntent.send();
                } catch (PendingIntent.CanceledException e) {
                    e.printStackTrace();
                }
            }
        });
        but_open1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlarmClock.ACTION_SET_TIMER)
                        .putExtra(AlarmClock.EXTRA_MESSAGE, "倒计时")
                        .putExtra(AlarmClock.EXTRA_LENGTH, 10)
                        .putExtra(AlarmClock.EXTRA_SKIP_UI, false);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
        but_open2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                        .putExtra(AlarmClock.EXTRA_MESSAGE, "闹铃")
                        .putExtra(AlarmClock.EXTRA_HOUR, 11)
                        .putExtra(AlarmClock.EXTRA_MINUTES, 32);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
        but_open3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HelpPopupManager.setEnabled(true);
                HelpPopupManager.startShowing();
            }
        });
    }

    @Override
    protected void initVariables() {

    }
}
