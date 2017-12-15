package com.youlehuo.app.aboutview.viewdoubleuse;

import android.widget.FrameLayout;
import android.widget.TextView;

import com.youlehuo.app.BaseActivity;
import com.youlehuo.app.R;

import butterknife.BindView;

/**
 * Created by xiaohe on 17-12-13.
 */

public class DoubleUseTwoActivity extends BaseActivity {
    public static TextView textView;
    @BindView(R.id.fl_two)
    FrameLayout fl_two;

    @Override
    protected int setView() {
        return R.layout.doubleusetwoactivity;
    }

    @Override
    protected void initView() {
        fl_two.addView(textView);
        textView.setText("hahaha--TWO--T");
    }

    @Override
    protected void initVariables() {

    }

    @Override
    public void finish() {
        fl_two.removeAllViews();
        textView.setText("hahaha--TWO--T--One");
        textView = null;
        super.finish();
    }
}
