package com.youlehuo.app.aboutview.bottomtabview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.youlehuo.app.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xiaohe on 17-11-10.
 */

public class BottomTabView extends LinearLayout {
    @BindView(R.id.vp_tab)
    ViewPager viewPager;
    @BindView(R.id.tab_bottom)
    TabLayout tabLayout;

    private Context context;

    public BottomTabView(Context context) {
        this(context, null);
    }

    public BottomTabView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomTabView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(HORIZONTAL);
        this.context = context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(context).inflate(R.layout.bottom_tabview, this, true);
        ButterKnife.bind(this, this);
        viewPager.setOffscreenPageLimit(1);
        tabLayout.setupWithViewPager(viewPager);

    }

    public void setBottomTabData(List<View> tabList) {
        if (tabList == null || tabList.size() == 0)
            return;
        for (int i = 0; i < tabList.size(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(((TextView) tabList.get(i)).getText()));
        }
    }

    public void setTabMode(boolean isFixed) {
        if (isFixed) {
            tabLayout.setTabMode(TabLayout.MODE_FIXED);
        } else {
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
    }

    List<String> fragments;
    Map<String, Fragment> fragmentList = new HashMap<>();

    public void setViewPager(List<String> fragments, FragmentManager manager) {
        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(manager);
        viewPager.setAdapter(viewPageAdapter);
        this.fragments = fragments;
        viewPageAdapter.notifyDataSetChanged();
    }

    private class ViewPageAdapter extends FragmentPagerAdapter {

        public ViewPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            if (fragmentList.containsKey(fragments.get(position))) {
                return fragmentList.get(fragments.get(position));
            } else {
                Fragment fragment = Fragment.instantiate(context, fragments.get(position));
                fragmentList.put(fragments.get(position), fragment);
                return fragment;
            }
        }

        @Override
        public int getCount() {
            return fragments == null ? 0 : fragments.size();
        }
    }
}

