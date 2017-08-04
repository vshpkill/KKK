package com.youlehuo.app.aboutview.flowlayout;

import android.view.LayoutInflater;
import android.widget.TextView;

import com.youlehuo.app.BaseActivity;
import com.youlehuo.app.R;

import butterknife.BindView;

/**
 * Created by dayima on 17-8-2.
 */

public class FlowLayoutActivity extends BaseActivity {
    private String[] mVals = new String[]
                    {"Hello", "Android",
                    "Weclome Hi ", "Button",
                    "TextView", "Hello", "Android",
                    "Weclome", "Button ImageView",
                    "TextView", "world",
                    "Andfsdfroid",
                    "Weclodsfsdfsdfme Hi ",
                    "Buttaaon", "f", "d", "Android",
                    "Weclome Hello", "Button Text",
                    "TextView",
                    "f", "a", "d Hi ",
                    "f", "w", "r", "fdsfsdfsdfsdfs",
                    "Asssssssssssssssssssssndroid",
                    "g ", "q", "e", "2"};

    @BindView(R.id.id_flowlayout)
    MyFlowView mFlowLayout;

    @Override
    protected int setView() {
        return R.layout.flowlayoutactivity;
    }

    @Override
    protected void initView() {
        LayoutInflater mInflater = LayoutInflater.from(this);
        for (int i = 0; i < mVals.length; i++) {
            TextView tv = (TextView) mInflater.inflate(R.layout.tv,
                    mFlowLayout, false);
            tv.setText(mVals[i]);
            mFlowLayout.addView(tv);
        }
    }

    @Override
    protected void initVariables() {

    }
}
