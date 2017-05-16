package com.youlehuo.app.aboutview.image.slice;

import android.widget.SeekBar;

import com.youlehuo.app.BaseActivity;
import com.youlehuo.app.R;

import butterknife.BindView;

/**
 * Created by dayima on 17-5-11.
 */

public class SliceActivity extends BaseActivity {
    @BindView(R.id.rl_slice)
    MyRelativeLayout rl_slice;
    @BindView(R.id.sb_slice)
    SeekBar sb_slice;
    @Override
    protected int setView() {
        return R.layout.sliceactivity;
    }

    @Override
    protected void initView() {
        rl_slice.setBackground(R.drawable.demo);
        sb_slice.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                rl_slice.setSliceNum(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    protected void initVariables() {

    }
}
