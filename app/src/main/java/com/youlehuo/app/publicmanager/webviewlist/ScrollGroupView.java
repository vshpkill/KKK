package com.youlehuo.app.publicmanager.webviewlist;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by xiaohe on 18-3-1.
 */

public class ScrollGroupView extends ViewGroup {

    private Scroller scroller;

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


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int startX = 0;
        int startY = 0;
        int endX = 0;
        int endY = 0;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = (int) event.getX();
                startY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                endX = (int) event.getX();
                endY = (int) event.getY();
                scroller.startScroll(0,startY,0,endY);
                invalidate();
                break;
        }
        return true;
    }

    int oldX,oldY;

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()){
            int x=scroller.getCurrX();
            int y=scroller.getCurrY();
            scrollBy(x-oldX, y-oldY);
            invalidate();
            oldX = x;
            oldY = y;
        }
    }

    /**
     * 与当前ViewGroup对应的LayoutParams
     */
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
