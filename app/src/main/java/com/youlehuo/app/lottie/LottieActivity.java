package com.youlehuo.app.lottie;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.youlehuo.app.BaseActivity;
import com.youlehuo.app.R;

import butterknife.BindView;

/**
 * Created by dayima on 17-6-28.
 */

public class LottieActivity extends BaseActivity {
    @BindView(R.id.but_lo)
    Button but_lo;
    @Override
    protected int setView() {
        return R.layout.lottieactivity;
    }

    @Override
    protected void initView() {
        but_lo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ClickActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initVariables() {

    }
}
