package com.youlehuo.app.aboutview.image;


import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by dayima on 17-5-10.
 */

public class MyImageView extends android.support.v7.widget.AppCompatImageView {

    public MyImageView(Context context) {
        super(context);
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //使控件可以横向填满父控件
        int width = Math.min(getMeasuredWidth(),getMeasuredHeight());
        setMeasuredDimension(width,width);
    }
}
