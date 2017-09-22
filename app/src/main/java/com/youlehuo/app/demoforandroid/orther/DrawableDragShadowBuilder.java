package com.youlehuo.app.demoforandroid.orther;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * Created by xiaohe on 17-9-22.
 */

public class DrawableDragShadowBuilder extends View.DragShadowBuilder {
    private Drawable mDrawable;

    public DrawableDragShadowBuilder(View view, Drawable drawable) {
        super(view);
//设置 Drawable 并使用一个绿色的过滤器
        mDrawable = drawable;
        mDrawable.setColorFilter(
                new PorterDuffColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY));
    }

    @Override
    public void onProvideShadowMetrics(Point shadowSize, Point touchPoint) {
//填充大小
        shadowSize.x = mDrawable.getIntrinsicWidth();
        shadowSize.y = mDrawable.getIntrinsicHeight();
//设置阴影相对于触摸点的位置
//这里阴影位于手指下方的中心
        touchPoint.x = mDrawable.getIntrinsicWidth() / 2;
        touchPoint.y = mDrawable.getIntrinsicHeight() / 2;
        mDrawable.setBounds(new Rect(0, 0, shadowSize.x, shadowSize.y));
    }

    @Override
    public void onDrawShadow(Canvas canvas) {
//在提供的 canvas 上绘制阴影视图
        mDrawable.draw(canvas);
    }
}