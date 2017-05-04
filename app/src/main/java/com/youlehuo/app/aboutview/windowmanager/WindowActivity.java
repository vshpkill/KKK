package com.youlehuo.app.aboutview.windowmanager;

import com.youlehuo.app.BaseActivity;
import com.youlehuo.app.R;

/**
 * Created by dayima on 17-2-13.
 */

public class WindowActivity extends BaseActivity {
    @Override
    protected int setView() {
        return R.layout.windowactivity;
    }

    @Override
    protected void initView() {
        getWindowManager();
    }

    @Override
    protected void initVariables() {

    }
}
