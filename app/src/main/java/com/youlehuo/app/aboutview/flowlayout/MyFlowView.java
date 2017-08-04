package com.youlehuo.app.aboutview.flowlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dayima on 17-8-2.
 */

public class MyFlowView extends ViewGroup {
    public MyFlowView(Context context) {
        this(context, null);
    }

    public MyFlowView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyFlowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int withMeasureSize = MeasureSpec.getSize(widthMeasureSpec);
        int withMeasureMode = MeasureSpec.getMode(widthMeasureSpec);

        int heightMeasureSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMeasureMode = MeasureSpec.getMode(heightMeasureSpec);

        int groupWith = 0;
        int groupHeight = 0;

        int lineWith = 0;
        int pl = getPaddingLeft();
        int pt = getPaddingTop();
        int pr = getPaddingRight();
        int pm = getPaddingBottom();
        int line = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) child.getLayoutParams();
            int childWith = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            if (i == 0) {
                groupHeight += childHeight + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
            }
            lineWith += (childWith + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin);
            if ((lineWith + pl + pr) > withMeasureSize) {
                line++;
                Log.e("line", "line~~" + line);
                groupHeight += childHeight + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
                groupWith = Math.max(groupWith, lineWith);
                lineWith = childWith + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
            }
        }
        withMeasureSize = withMeasureMode == MeasureSpec.EXACTLY ? withMeasureSize : (groupWith + pl + pr);
        heightMeasureSize = heightMeasureMode == MeasureSpec.EXACTLY ? heightMeasureSize : (groupHeight + pt + pm);
        setMeasuredDimension(withMeasureSize, heightMeasureSize);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childl = 0;
        int childt = 0;
        int childr = 0;
        int childb = 0;

        int with = getMeasuredWidth();

        int linNum = 1;

        int pl = getPaddingLeft();
        int pt = getPaddingTop();
        int pr = getPaddingRight();

        int lineWith = 0;

        int childCount = getChildCount();

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) child.getLayoutParams();
            int childWith = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();

            lineWith += (childWith + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin);

            if ((lineWith + pl + pr) > with) {
                linNum++;
                childl = marginLayoutParams.leftMargin + pl;
                lineWith = childWith + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
                childr = lineWith + pl - marginLayoutParams.rightMargin;
            } else {
                if (i == 0) {
                    childl = marginLayoutParams.leftMargin + pl;
                } else {
                    childl = lineWith + pl - childWith - marginLayoutParams.leftMargin;
                }
                childr = lineWith + pl - marginLayoutParams.rightMargin;
            }


            childt = linNum * (childHeight + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin) - childHeight - marginLayoutParams.bottomMargin + pt;
            childb = linNum * (childHeight + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin) - marginLayoutParams.bottomMargin + pt;
            child.layout(childl, childt, childr, childb);
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
