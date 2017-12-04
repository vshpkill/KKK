package com.youlehuo.app;

import android.view.LayoutInflater;

import java.util.LinkedHashMap;

/**
 * Created by xiaohe on 17-11-27.
 *
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
        LinkedHashMap<String,String> linkedHashMap = new LinkedHashMap<String,String>(0,0.5f,true);

    }
}