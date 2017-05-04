package com.youlehuo.app.aboutview.recycleview.citylist;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by dayima on 17-1-11.
 */

public class CircleTextView extends TextView {
    private Paint mBgPaint = new Paint();

    private PaintFlagsDrawFilter pfd = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
    public CircleTextView(Context context) {
        this(context,null);
    }
    public void setBackGroundColor(int color) {
        mBgPaint.setColor(color);
    }
    public CircleTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredWith = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int max = Math.max(measuredHeight, measuredWith);
        setMeasuredDimension(max, max);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.setDrawFilter(pfd);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, Math.max(getWidth(), getHeight()) / 2, mBgPaint);
        super.onDraw(canvas);
    }
}
