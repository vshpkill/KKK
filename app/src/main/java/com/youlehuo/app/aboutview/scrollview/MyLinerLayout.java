package com.youlehuo.app.aboutview.scrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * Created by dayima on 16-12-14.
 */

public class MyLinerLayout extends LinearLayout {
    private int yDown,yMove;
    private int mMove;
    private int i=0;
    private boolean isIntercept = false;
    private ScrollView scrollView;
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
        scrollView = (ScrollView) getChildAt(0);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int y = (int) ev.getY();
        int mScrollY = scrollView.getScrollY();
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                yDown = y;
                break;
            case MotionEvent.ACTION_MOVE:
                yMove = y;
                if (yMove - yDown>0&&mScrollY==0){
                    if (!isIntercept){
                        yDown = (int) ev.getY();
                        isIntercept = true;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                layout(getLeft(),getTop()-i,getRight(),getBottom()-i);
                i = 0;
                isIntercept = false;
                break;
        }
        if (isIntercept) {
            mMove = yMove - yDown;
            i += mMove;
            layout(getLeft(), getTop() + mMove, getRight(), getBottom() + mMove);
        }
        return isIntercept;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        onInterceptTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }
}
