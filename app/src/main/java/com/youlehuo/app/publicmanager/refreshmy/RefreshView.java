package com.youlehuo.app.publicmanager.refreshmy;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.youlehuo.app.R;

/**
 * Created by xiaohe on 18-2-26.
 */

public class RefreshView extends SmartRefreshLayout {
    public RefreshView(Context context) {
        super(context);
        initView();
    }

    public RefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public RefreshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        setRefreshHeader(new MyHeadView(getContext()));
        setHeaderHeight(60);
    }

    class MyHeadView extends FrameLayout implements RefreshHeader {

        private ImageView logo;
        private ImageView load;
        private AnimationDrawable frameAnim;
        private Animation animatorOut, animatorIn;
        private LinearLayout lin_load;
        private TextView tv_load;

        public MyHeadView(Context context) {
            super(context);
            initView(context);
        }

        public MyHeadView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
            initView(context);
        }

        public MyHeadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            initView(context);
        }

        private void initView(Context context) {
            LayoutInflater.from(context).inflate(R.layout.refreshheadview, this, true);
            logo = (ImageView) findViewById(R.id.im_logo);
            lin_load = (LinearLayout) findViewById(R.id.lin_load);
            load = (ImageView) findViewById(R.id.im_load);
            tv_load = (TextView) findViewById(R.id.tv_load);
            frameAnim = (AnimationDrawable) getResources().getDrawable(R.drawable.anim_list);
            // 把AnimationDrawable设置为ImageView的背景
            load.setBackground(frameAnim);

            animatorOut = AnimationUtils.loadAnimation(context, R.anim.view_animation);
            animatorIn = AnimationUtils.loadAnimation(context, R.anim.view_load);
        }

        @Override
        public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {
            Log.e("刷新状态", "oldState：" + oldState + "----newState:" + newState);
            switch (newState) {
                case None:
                case PullDownToRefresh:
                    //下拉开始刷新
                    logo.clearAnimation();
                    lin_load.clearAnimation();
                    logo.setVisibility(VISIBLE);//显示下拉箭头
                    lin_load.setVisibility(GONE);//隐藏动画
                    break;
                case Refreshing:
                    //正在刷新
//                    logo.startAnimation(animatorOut);
//                    frameAnim.start();
//                    lin_load.setVisibility(VISIBLE);//显示加载动画
//                    lin_load.startAnimation(animatorIn);
//                    tv_load.setText("正在加载");
//                    logo.setVisibility(GONE);//隐藏箭头
                    break;
                case ReleaseToRefresh:
                    //释放立即刷新
                    break;
                case RefreshReleased:
                    logo.startAnimation(animatorOut);
                    frameAnim.start();
                    lin_load.setVisibility(VISIBLE);//显示加载动画
                    lin_load.startAnimation(animatorIn);
                    tv_load.setText("正在加载");
                    break;
            }
        }

        @NonNull
        @Override
        public View getView() {
            return this;
        }

        @Override
        public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
            frameAnim.stop();
            return 500;
        }

        @NonNull
        @Override
        public SpinnerStyle getSpinnerStyle() {
            return SpinnerStyle.Scale;
        }

        @Override
        public void setPrimaryColors(int... colors) {

        }

        @Override
        public void onInitialized(@NonNull RefreshKernel kernel, int height, int extendHeight) {
            Log.e("刷新状态onInitialized","RefreshKernel:"+kernel+"---height:"+height+"----extendHeight:"+extendHeight);
        }

        @Override
        public void onPulling(float percent, int offset, int height, int extendHeight) {
            Log.e("刷新状态onPulling","percent:"+percent+"---offset:"+offset+"----height:"+height+"------extendHeight:"+extendHeight);
        }

        @Override
        public void onReleasing(float percent, int offset, int height, int extendHeight) {
            Log.e("刷新状态onReleasing","percent:"+percent+"---offset:"+offset+"----height:"+height+"------extendHeight:"+extendHeight);
        }

        @Override
        public void onReleased(RefreshLayout refreshLayout, int height, int extendHeight) {

        }

        @Override
        public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int extendHeight) {

        }

        @Override
        public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

        }

        @Override
        public boolean isSupportHorizontalDrag() {
            return false;
        }
    }
}
