package com.youlehuo.app.four_zujian.service.dayima;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.youlehuo.app.BaseActivity;
import com.youlehuo.app.R;

import butterknife.BindView;

public class StrongServiceActivity extends BaseActivity {
    @BindView(R.id.tv_hello)
    TextView tv_hello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected int setView() {
        return R.layout.strongserviceactivity;
    }

    @Override
    protected void initView() {
        tv_hello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(StrongServiceActivity.this, Service1.class);
                startService(i1);

                Intent i2 = new Intent(StrongServiceActivity.this, Service2.class);
                startService(i2);
            }
        });
    }

    @Override
    protected void initVariables() {

    }
}
