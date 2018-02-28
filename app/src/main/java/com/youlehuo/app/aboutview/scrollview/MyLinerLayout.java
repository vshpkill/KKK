package com.youlehuo.app.aboutview.scrollview;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by dayima on 16-12-14.
 */

public class MyLinerLayout extends LinearLayout {
    private boolean mRefreshing;
    private int yDown, yMove;
    private int mMove;
    private int i = 0;
    private boolean isIntercept = false;
    private View scrollView;

    public MyLinerLayout(Context context) {
        super(context);
    }

    public MyLinerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        scrollView = getChildAt(0);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int y = (int) ev.getY();
        if (!isEnabled() || canChildScrollUp() || mRefreshing) {
            isIntercept = false;
        }
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                yDown = y;
                break;
            case MotionEvent.ACTION_MOVE:
                yMove = y;
                Log.e("滑动距离", "yMove：" + yMove);
                if (yMove - yDown > 0) {
                    if (!isIntercept) {
                        yDown = (int) ev.getY();
                        Log.e("滑动距离", "yDown：" + yDown);
                        isIntercept = true;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
//                layout(getLeft(), getTop() - i, getRight(), getBottom() - i);
                scrollTo(0,0);
                i = 0;
                isIntercept = false;
                break;
        }
        if (isIntercept) {
            mMove = (yMove - yDown)/3;
            Log.e("滑动距离", "mMove：" + mMove);
            if (i<=500){
                i += mMove;
//                layout(getLeft(), getTop() + mMove, getRight(), getBottom() + mMove);
                scrollTo(0,-i);
            }
        }
        return isIntercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {

        }
        return super.onTouchEvent(event);
    }

    private boolean canChildScrollUp() {
        return ViewCompat.canScrollVertically(scrollView, -1);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        onInterceptTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }
}
