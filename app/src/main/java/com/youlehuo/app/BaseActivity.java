package com.youlehuo.app;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by dayima on 16-12-27.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected Context context;
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
}
