package com.youlehuo.app.aboutview.bottomtabview;

import android.view.View;
import android.widget.TextView;

import com.youlehuo.app.BaseActivity;
import com.youlehuo.app.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by xiaohe on 17-11-10.
 * 底部导航+内容切换
 */

public class BTabViewActivity extends BaseActivity {
    @BindView(R.id.btv_demo)
    BottomTabView bottomTabView;

    @Override
    protected int setView() {
        return R.layout.btabviewactivity;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initVariables() {
        List<View> viewList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            TextView textView = new TextView(context);
            textView.setText("tab" + i);
            viewList.add(textView);
        }
        bottomTabView.setTabMode(true);
        bottomTabView.setBottomTabData(viewList);

        List<String> fragments = new ArrayList<>();
        fragments.add(FragmentTabOne.class.getName());
        fragments.add(FragmentTabTwo.class.getName());
        fragments.add(FragmentTabThree.class.getName());
        fragments.add(FragmentTabFour.class.getName());
        fragments.add(FragmentTabFive.class.getName());
        bottomTabView.setViewPager(fragments, getSupportFragmentManager());
    }
}
