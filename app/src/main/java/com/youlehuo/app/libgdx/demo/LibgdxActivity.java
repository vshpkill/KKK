package com.youlehuo.app.libgdx.demo;

import com.youlehuo.app.BaseActivity;
import com.youlehuo.app.R;

/**
 * Created by dayima on 17-6-12.
 */

public class LibgdxActivity extends BaseActivity {
    GiftParticleFragment particleFragment;

    @Override
    protected int setView() {
        return R.layout.libgdxactivity;
    }

    @Override
    protected void initView() {
        particleFragment = (GiftParticleFragment) getSupportFragmentManager().findFragmentById(R.id.fm_gdx);
    }

    @Override
    protected void initVariables() {
    }
}
