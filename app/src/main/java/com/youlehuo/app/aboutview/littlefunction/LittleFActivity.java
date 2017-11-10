package com.youlehuo.app.aboutview.littlefunction;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.youlehuo.app.BaseActivity;
import com.youlehuo.app.R;

/**
 * Created by xiaohe on 17-11-10.
 */

public class LittleFActivity extends BaseActivity {
    @Override
    protected int setView() {
        return R.layout.littlefactivity;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initVariables() {

    }

    public void hideInput(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
}
