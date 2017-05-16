package com.youlehuo.app.aboutview.image.slice;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.kkk.utils.util.DensityUtil;
import com.youlehuo.app.R;

import java.util.List;

/**
 * Created by dayima on 17-5-11.
 */

public class MyRelativeLayout extends RelativeLayout{
    private Bitmap clBitmap;
    private int slice;
    private int mergin;
    private int padding;
    public MyRelativeLayout(Context context) {
        this(context,null);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }
    public void setBackground(int resources){
        setBackground(resources);
        clBitmap = BitmapFactory.decodeResource(getResources(),resources);
        List<SliceBean> beanList = SliceUtils.splitPic(clBitmap,slice,false);
    }
    public void setSliceNum(int slice){
        this.slice = slice;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = Math.min(getMeasuredWidth(),getMeasuredHeight());
        setMeasuredDimension(width,width);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.my_relative);
        slice = typedArray.getInt(R.styleable.my_relative_num,1);
        typedArray.recycle();
        mergin = DensityUtil.dip2px(context,1);
        padding = getMin(getPaddingLeft(),getPaddingTop(),getPaddingRight(),getPaddingBottom());
        if (padding == 0){
            padding = mergin;
        }
    }

    private int getMin(int... mins) {
        int min = mins[0];
        for (int m : mins){
            if (min<m){
                min = m;
            }
        }
        return min;
    }
}
