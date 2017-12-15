package com.youlehuo.app.aboutview.viewdoubleuse;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.youlehuo.app.BaseActivity;
import com.youlehuo.app.R;

import butterknife.BindView;

/**
 * Created by xiaohe on 17-12-13.
 */

public class DoubleUseOneActivity extends BaseActivity {
    @BindView(R.id.but_next)
    Button but_next;

    @BindView(R.id.fl_one)
    FrameLayout fl_one;
    private TextView textView;

    @Override
    protected int setView() {
        return R.layout.doubleuseoneactivity;
    }

    @Override
    protected void initView() {
        textView = new TextView(this);
        textView.setText("hahaha");
        fl_one.addView(textView);

        but_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fl_one.removeAllViews();
                textView.setText("hahaha--TWO");
                DoubleUseTwoActivity.textView = textView;
                Intent intent = new Intent(context,DoubleUseTwoActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (fl_one.getChildCount()==0&&textView!=null){
            fl_one.addView(textView);
        }else {

        }
    }
}
