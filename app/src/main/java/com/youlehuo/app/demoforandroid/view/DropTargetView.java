package com.youlehuo.app.demoforandroid.view;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.View;

/**
 * Created by xiaohe on 17-9-22.
 */

public class DropTargetView extends AppCompatImageView implements View.OnDragListener {
    private boolean mDropped;

    public DropTargetView(Context context) {
        this(context, null);
    }

    public DropTargetView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DropTargetView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDragView();
    }

    private void initDragView() {
        setOnDragListener(this);
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        PropertyValuesHolder pvhX, pvhY;
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                pvhX = PropertyValuesHolder.ofFloat("scaleX", 0.5f);
                pvhY = PropertyValuesHolder.ofFloat("scaleY", 0.5f);
                ObjectAnimator.ofPropertyValuesHolder(this, pvhX, pvhY).start();
//                setImageDrawable(null);
                mDropped = false;
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                //拖动进入到边界区域时,视图会稍微放大一下
                pvhX = PropertyValuesHolder.ofFloat("scaleX", 0.75f);
                pvhY = PropertyValuesHolder.ofFloat("scaleY", 0.75f);
                ObjectAnimator.ofPropertyValuesHolder(this, pvhX, pvhY).start();
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                //拖动离开视图时,会恢复到之前的大小
                pvhX = PropertyValuesHolder.ofFloat("scaleX", 0.5f);
                pvhY = PropertyValuesHolder.ofFloat("scaleY", 0.5f);
                ObjectAnimator.ofPropertyValuesHolder(this, pvhX, pvhY).start();
                break;
            case DragEvent.ACTION_DROP:
                //拖动后放置时会有一段简短的帧动画并将视图的图片设置为拖动事件传入的 drawable 图片
                //这个动画会收缩一下视图,然后再还原
                Keyframe frame0 = Keyframe.ofFloat(0f, 0.75f);
                Keyframe frame1 = Keyframe.ofFloat(0.5f, 0f);
                Keyframe frame2 = Keyframe.ofFloat(1f, 0.75f);
                pvhX = PropertyValuesHolder.ofKeyframe("scaleX", frame0, frame1,
                        frame2);
                pvhY = PropertyValuesHolder.ofKeyframe("scaleY", frame0, frame1,
                        frame2);
                ObjectAnimator.ofPropertyValuesHolder(this, pvhX, pvhY).start();
                //DragEvent 中传递的 Object 设置我们的图片
                setImageDrawable((Drawable) event.getLocalState());
                //我们设置放置标识让 ENDED 动画不再运行
                mDropped = true;
                break;
            default:
                //忽略我们不感兴趣的事件
                return false;
        }
        //处理所有感兴趣的事件
        return true;
    }
}