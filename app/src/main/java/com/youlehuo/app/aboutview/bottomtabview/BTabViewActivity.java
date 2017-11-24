package com.youlehuo.app.aboutview.bottomtabview;

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
        List<String> viewList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            TextView textView = new TextView(context);
            textView.setText("tab" + i);
            textView.setTextColor(0xFFfefe6e);
            viewList.add("tab" + i);
        }
        List<String> fragments = new ArrayList<>();

        FragmentTabOne tabOne = new FragmentTabOne();
        FragmentTabTwo tabTwo = new FragmentTabTwo();
        FragmentTabThree tabThree = new FragmentTabThree();
        FragmentTabFour tabFour = new FragmentTabFour();
        FragmentTabFive tabFive = new FragmentTabFive();


        fragments.add(FragmentTabOne.class.getName());
        fragments.add(FragmentTabTwo.class.getName());
        fragments.add(FragmentTabThree.class.getName());
        fragments.add(FragmentTabFour.class.getName());
        fragments.add(FragmentTabFive.class.getName());
//        fragments.add(tabOne);
//        fragments.add(tabTwo);
//        fragments.add(tabThree);
//        fragments.add(tabFour);
//        fragments.add(tabFive);
        bottomTabView.setBottomTabData(viewList);
        bottomTabView.setViewPager(fragments, getSupportFragmentManager());
        bottomTabView.setTabMode(true);
        bottomTabView.setTabView();
    }
}
