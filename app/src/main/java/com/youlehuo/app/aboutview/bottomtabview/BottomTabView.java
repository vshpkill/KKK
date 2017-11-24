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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.youlehuo.app.R;

import java.util.List;

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

    List<String> tabList;

    public void setBottomTabData(List<String> tabList) {
        if (tabList == null || tabList.size() == 0)
            return;
        this.tabList = tabList;
//            tabLayout.addTab(tabLayout.newTab().setText(((TextView) tabList.get(i)).getText()), i);
        initEvents();
    }

    public void setTabMode(boolean isFixed) {
        if (isFixed) {
            tabLayout.setTabMode(TabLayout.MODE_FIXED);
        } else {
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
    }

    List<String> fragments;

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
            return Fragment.instantiate(context, fragments.get(position));
//            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments == null ? 0 : fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabList.get(position);
        }
    }

    public void setTabView() {
        TextView textView = new TextView(context);
        textView.setText("替换而已");
        textView.setTextColor(0xFFfefe6e);
        tabLayout.getTabAt(0).setCustomView(textView);
    }

    private void initEvents() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab == tabLayout.getTabAt(0)) {
                } else if (tab == tabLayout.getTabAt(1)) {
                } else if (tab == tabLayout.getTabAt(2)) {
                } else if (tab == tabLayout.getTabAt(3)) {
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (tab == tabLayout.getTabAt(0)) {
                } else if (tab == tabLayout.getTabAt(1)) {
                } else if (tab == tabLayout.getTabAt(2)) {
                } else if (tab == tabLayout.getTabAt(3)) {
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}

