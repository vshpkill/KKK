package com.youlehuo.app.publicmanager.webviewlist;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Scroller;

/**
 * Created by xiaohe on 18-3-1.
 */

public class ScrollGroupView extends ViewGroup {
    private static final String TAG = "ScrollGroupView";
    private Scroller scroller;
    private int screenHeight;
    public ScrollGroupView(Context context) {
        super(context);
        initView(context);
    }

    public ScrollGroupView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ScrollGroupView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        screenHeight = getScreenSize(context).heightPixels;
        scroller = new Scroller(context);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int WMeaserMode = MeasureSpec.getMode(widthMeasureSpec);
        int WMeaserSize = MeasureSpec.getSize(widthMeasureSpec);
        int HMeaserMode = MeasureSpec.getMode(heightMeasureSpec);
        int HMeaserSize = MeasureSpec.getSize(heightMeasureSpec);

        int paddingTop = getPaddingTop();
        int paddingLeft = getPaddingLeft();
        int paddingBottom = getPaddingBottom();
        int paddingRight = getPaddingRight();

        int width = 0;
        int height = 0;
        int childCount = getChildCount();

        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) childView.getLayoutParams();
            int childWidth = childView.getMeasuredWidth() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
            int childHeight = childView.getMeasuredHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
            width = width > childWidth ? width : childWidth;
            height += childHeight;
        }

        WMeaserSize = WMeaserMode == MeasureSpec.EXACTLY ? WMeaserSize : width + paddingLeft + paddingRight;
        HMeaserSize = HMeaserMode == MeasureSpec.EXACTLY ? HMeaserSize : height + paddingTop + paddingBottom;
        setMeasuredDimension(MeasureSpec.makeMeasureSpec(WMeaserSize, WMeaserMode), MeasureSpec.makeMeasureSpec(HMeaserSize, HMeaserMode));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int paddingTop = getPaddingTop();
        int paddingLeft = getPaddingLeft();
        int paddingBottom = getPaddingBottom();
        int paddingRight = getPaddingRight();

        int childl = 0;
        int childt = 0;
        int childr = 0;
        int childb = 0;

        int beforeWidth = 0;
        int beforeHeight = 0;

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) childView.getLayoutParams();

            childl = marginLayoutParams.leftMargin;
            childt += (marginLayoutParams.topMargin + beforeHeight);
            childr = childView.getMeasuredWidth() + marginLayoutParams.leftMargin;
            childb += (childView.getMeasuredHeight() + marginLayoutParams.bottomMargin + beforeHeight);

            beforeWidth = childView.getMeasuredHeight();
            beforeHeight = childView.getMeasuredHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
            childView.layout(childl + paddingLeft, childt + paddingTop, childr, childb);
        }
    }

    int lastDownY = 0;
    private float mScrollStart;
    private float mScrollEnd;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int startX = 0;

        int endX = 0;
        int endY = 0;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mScrollStart = getScrollY();
                lastDownY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float currentY = event.getY();
                float dy;
                dy = lastDownY - currentY;
                if (getScrollY()<0){
                    dy = 0;
                }else if (getScrollY()>getHeight()-screenHeight){
                    dy = 0;
                }
                scrollBy(0, (int) dy);
                lastDownY = (int) event.getY();
                /*dy = (int) (event.getY() - startY);
//                if (getScrollY() < 0) {
//                    dy = 0;
//                } else if (getScrollY() > getHeight() - screenHeight) {
//                    dy = 0;
//                }


                if (event.getY()<0){
                    dy = 0;
                }else if (event.getY()>getHeight()){
                    dy = getHeight();
                }
                scroller.startScroll(0,0,startY, dy);
                invalidate();
//                scrollBy(0, dy);*/
                break;
            case MotionEvent.ACTION_UP:
                mScrollEnd = getScrollY();
                int dScrollY = (int) (mScrollEnd - mScrollStart);
                if (mScrollEnd < 0) {// 最顶端：手指向下滑动，回到初始位置
                    Log.d(TAG, "mScrollEnd < 0" + dScrollY);
                    scroller.startScroll(0, getScrollY(), 0, -getScrollY());
                } else if (mScrollEnd > getHeight() - screenHeight) {//已经到最底端，手指向上滑动回到底部位置
                    Log.d(TAG, "getHeight() - mScreenHeight - (int) mScrollEnd " + (getHeight() - screenHeight - (int) mScrollEnd));
                    scroller.startScroll(0, getScrollY(), 0, getHeight() - screenHeight - (int) mScrollEnd);
                }
                postInvalidate();// 重绘执行computeScroll()
//                endX = (int) event.getX();
//                endY = (int) event.getY();
//                invalidate();
                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()){
            scrollTo(0, scroller.getCurrY());
            postInvalidate();
        }
    }

    /**
     * 与当前ViewGroup对应的LayoutParams
     */
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    public static DisplayMetrics getScreenSize(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }
}
