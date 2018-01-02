package com.youlehuo.app;

import android.app.Instrumentation;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.MotionEvent;

/**
 * Created by xiaohe on 17-11-27.
 */

public class TestActivity extends BaseActivity {
    @Override
    protected int setView() {
        return 0;
    }

    @Override
    protected void initView() {
        LayoutInflater.from(this);
    }

    @Override
    protected void initVariables() {
        Instrumentation mInst = new Instrumentation();
        mInst.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(),
                SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, 100, 200, 0));    //x,y 即是事件的坐标
        mInst.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(),
                SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, 100, 200, 0));
    }
}