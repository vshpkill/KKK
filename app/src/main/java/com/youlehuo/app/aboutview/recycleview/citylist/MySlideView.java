package com.youlehuo.app.aboutview.recycleview.citylist;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import com.youlehuo.app.R;

import java.util.List;

/**
 * Created by dayima on 17-1-11.
 */

public class MySlideView extends View{
    public interface onTouchListener{
        void showTextView(String textView, boolean dismiss);
    }
    private onTouchListener listener;
    private Paint paint;
    private int mWidth, mHeight, mTextHeight, position;
    private int yDown, yMove, mTouchSlop;
    private boolean isSlide;
    private Rect mBound;
    private List<String> list;
    private int backgroundColor;
    private int count;
    private String selectTxt;
    public MySlideView(Context context) {
        this(context,null);
    }

    public MySlideView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MySlideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        //ViewConfiguration这个类主要定义了UI中所使用到的标准常量，像超时、尺寸、距离
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    private void initView() {
        paint = new Paint();
        paint.setAntiAlias(true);
        mBound = new Rect();
        backgroundColor = getResources().getColor(R.color.font_info);
    }

    /**
     * 设置垂直方向条目
     * @param list
     */
    public void setHeightItemCount(List<String> list,onTouchListener listener){
        this.count = list.size();
        this.list = list;
        this.listener =listener;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (count!=0){
            mTextHeight = mHeight/count;
        }else {
            throw (new RuntimeException("垂直方向item条目数未设置,请先调用setHeightItemCount()方法进行设置"));
        }
        paint.setColor(backgroundColor);
        canvas.drawRect(0,0,mWidth,mHeight,paint);
        for (int i = 0;i<list.size();i++){
            String textView = list.get(i);
            if (i == position -1){
                paint.setColor(getResources().getColor(R.color.oranges));
                selectTxt = list.get(i);
                listener.showTextView(selectTxt, false);
            }else {
                paint.setColor(getResources().getColor(R.color.white));
            }
            paint.setTextSize(40);
            //将text宽高信息封装到mBound
            paint.getTextBounds(textView,0,textView.length(),mBound);
            canvas.drawText(textView,(mWidth-mBound.width())/2,mTextHeight-mBound.height(),paint);
            mTextHeight += mHeight/count;
        }
        Log.e("刷新界面","刷新刷新");
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int y = (int) event.getY();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                yDown = y;
                break;
            case MotionEvent.ACTION_MOVE:
                yMove = y;
                int dy = yMove - yDown;
                //与系统认定的滑动最小值比较判断是否为y轴滑动
                if (Math.abs(dy)>mTouchSlop){
                    isSlide = true;
                }
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int y = (int) event.getY();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                backgroundColor = getResources().getColor(R.color.font_text);
                mTextHeight = mHeight/count;
                position = y/(mHeight/(count+1));
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                if (isSlide){
                    backgroundColor = getResources().getColor(R.color.font_text);
                    mTextHeight = mHeight/count;
                    position = y/(mHeight/(count+1));
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                backgroundColor = getResources().getColor(R.color.font_info);
                mTextHeight = mHeight / count;
                position = 0;
                invalidate();
                listener.showTextView(selectTxt, true);
                break;
        }
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        if(widthMode == MeasureSpec.EXACTLY){
            width = widthSize;
        }else {
            width = widthSize*1/2;
        }

        if (heightMode == MeasureSpec.EXACTLY){
            height = heightSize;
        }else {
            height = heightSize*1/2;
        }
        mWidth = width;
        mHeight = height;
        //setMeasuredDimension方法决定了当前View的大小，必须由onMeasure(int, int)来调用，来存储测量的宽，高值。
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
//        setMeasuredDimension(width,height);
    }

}
