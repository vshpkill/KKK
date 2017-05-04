package com.youlehuo.app.aboutview.viewpager;

import android.animation.ArgbEvaluator;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.AccelerateInterpolator;
import android.widget.Toast;

import com.youlehuo.app.BaseActivity;
import com.youlehuo.app.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by dayima on 17-3-6.
 */

public class ViewPagerActivity extends BaseActivity implements ViewPager.OnPageChangeListener{
    @BindView(R.id.vp_head)
    ViewPager vp_head;
    @BindView(R.id.vp_indicator)
    ViewPagerIndicator vp_indicator;

    private int mCurrentPosition;
    private List<View> viewList;
    private int arbg[] = {R.drawable.vp_bg1,R.drawable.vp_bg2,R.drawable.vp_bg3};
    private int mAutoRollingTime = 2500;
    private int mReleasingTime = 0;
    private static final int MESSAGE_AUTO_ROLLING = 0X1001;
    private static final int MESSAGE_AUTO_ROLLING_CANCEL = 0X1002;
    private ViewPagerAdapter pagerAdapter;

    @Override
    protected int setView() {
        return R.layout.viewpageractivity;
    }
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MESSAGE_AUTO_ROLLING:
                    if(mCurrentPosition == pagerAdapter.getCount() - 1){
                        vp_head.setCurrentItem(0,true);
                    }else {
                        vp_head.setCurrentItem(mCurrentPosition + 1,true);
                    }
                    postDelayed(mAutoRollingTask,mAutoRollingTime);
                    break;
                case MESSAGE_AUTO_ROLLING_CANCEL:
                    postDelayed(mAutoRollingTask,mAutoRollingTime);
                    break;
            }
        }
    };
    private int mViewPagerScrollState;
    private Runnable mAutoRollingTask = new Runnable() {
        @Override
        public void run() {
            int now = (int) System.currentTimeMillis();
            int timediff = mAutoRollingTime;
            if(mReleasingTime != 0){
                timediff = now - mReleasingTime;
            }
            if(mViewPagerScrollState == ViewPager.SCROLL_STATE_IDLE){
                //if user's finger just left the screen,we should wait for a while.
                if(timediff >= mAutoRollingTime * 0.8){
                    mHandler.sendEmptyMessage(MESSAGE_AUTO_ROLLING);
                }else {
                    mHandler.sendEmptyMessage(MESSAGE_AUTO_ROLLING_CANCEL);
                }
            }else if(mViewPagerScrollState == ViewPager.SCROLL_STATE_DRAGGING){
                mHandler.sendEmptyMessage(MESSAGE_AUTO_ROLLING_CANCEL);
            }
        }
    };
    FixedSpeedScroller mScroller = null;
    @Override
    protected void initView() {
        try {
            Field mField;
            mField = ViewPager.class.getDeclaredField("mScroller");
            mField.setAccessible(true);
            mScroller = new FixedSpeedScroller(vp_head.getContext(),
                    new AccelerateInterpolator());
            mScroller.setmDuration(300); // 500ms
            mField.set(vp_head, mScroller);
        } catch (Exception e) {
            e.printStackTrace();
        }

        View view1 = View.inflate(context, R.layout.viewpager_1, null);
        View view2 = View.inflate(context, R.layout.viewpager_2, null);
        View view3 = View.inflate(context, R.layout.viewpager_3, null);
        viewList = new ArrayList<>();
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        size = viewList.size();
        vp_indicator.setItemCount(size);
        vp_indicator.setRadius(25);
        vp_indicator.setPadding(50);
    }

    @Override
    protected void initVariables() {
        pagerAdapter = new ViewPagerAdapter();
        vp_head.setAdapter(pagerAdapter);
        vp_head.addOnPageChangeListener(this);
        pagerAdapter.notifyDataSetChanged();
        vp_head.setCurrentItem(Integer.MAX_VALUE/2);
        vp_head.postDelayed(mAutoRollingTask,mAutoRollingTime);
    }
    private int size;
    class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            //view比较(key值获取的view)
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            container.removeView(viewList.get(position%(size)));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = viewList.get(position%size);
            ViewParent viewParent = view.getParent();
            if (viewParent!=null){
                ((ViewGroup)viewParent).removeView(view);
            }
            container.addView(view);
            switch (position%size){
                case 0:
                    viewList.get(position%size).findViewById(R.id.but_1).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(context,"1",Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;
                case 1:
                    viewList.get(position%size).findViewById(R.id.but_2).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(context,"2",Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;
                case 2:
                    viewList.get(position%size).findViewById(R.id.but_3).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(context,"3",Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;
                default:
                    break;
            }
            //return的值为key值
            return viewList.get(position%size);
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }
    }
    int mColorsFrom[] = {Color.parseColor("#34a7ff"), Color.parseColor("#ef0000"), Color.parseColor("#d000ef")};
    int endFrom[] = {Color.parseColor("#00d2ff"), Color.parseColor("#ff7272"), Color.parseColor("#ff72fa")};
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int count = position%size;
        ArgbEvaluator evaluator = new ArgbEvaluator(); // ARGB求值器(背景渐变)
        int endValue = count == (size-1)?mColorsFrom[0]:mColorsFrom[count+1];
        int evaluate = (Integer) evaluator.evaluate(positionOffset, mColorsFrom[position%size], endValue);

        int endValue1 = count == (size-1)?endFrom[0]:endFrom[count+1];
        int evaluate1 = (Integer) evaluator.evaluate(positionOffset, endFrom[position%size], endValue1);

        vp_head.setBackgroundDrawable(ScrimUtil.makeCubicGradientScrimDrawable(
                evaluate1, //颜色
                evaluate,
                8, //渐变层数
                Gravity.TOP));

        vp_indicator.setPositionAndOffset(position%size,positionOffset);
    }

    @Override
    public void onPageSelected(int position) {
//        vp_head.setBackgroundResource(arbg[position%size]);
        mCurrentPosition = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if(state == ViewPager.SCROLL_STATE_DRAGGING){
            mViewPagerScrollState = ViewPager.SCROLL_STATE_DRAGGING;
        }else if(state == ViewPager.SCROLL_STATE_IDLE){
            mReleasingTime = (int) System.currentTimeMillis();
            mViewPagerScrollState = ViewPager.SCROLL_STATE_IDLE;
        }
    }
}
