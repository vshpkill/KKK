package com.youlehuo.app;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import butterknife.ButterKnife;

/**
 * Created by dayima on 16-12-27.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected Context context;
    private float sx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(setView());
        ButterKnife.bind(this);
        initView();
        initVariables();
    }

    protected abstract int setView();

    protected abstract void initView();

    protected abstract void initVariables();


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                sx = ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:
                if (sx - ev.getX()>300){
                    finish();
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
