package com.youlehuo.app.four_zujian.intentaction;

import com.youlehuo.app.BaseActivity;
import com.youlehuo.app.R;

/**
 * Created by dayima on 17-1-6.
 */

public class IntentActivity extends BaseActivity {
    public static final String ACTION_TIMETRAVEL = "com.example.action.TIMETRAVEL";
    /**
     * Android 5.0以后规定必须显式启动Service
     * @return
     */
    @Override
    protected int setView() {
        return R.layout.intentactivity;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initVariables() {

    }
}
