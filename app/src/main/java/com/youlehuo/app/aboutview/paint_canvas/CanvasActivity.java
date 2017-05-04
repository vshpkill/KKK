package com.youlehuo.app.aboutview.paint_canvas;

import com.youlehuo.app.BaseActivity;
import com.youlehuo.app.R;

import butterknife.BindView;

/**
 * Created by dayima on 17-1-12.
 */

public class CanvasActivity extends BaseActivity {
    @BindView(R.id.cus_main)
    CustomView customView;

    @Override
    protected int setView() {
        return R.layout.canvasactivity;
    }

    @Override
    protected void initView() {

    }
    @Override
    protected void initVariables() {

    }
}
