package com.youlehuo.app.aboutview.paint_canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.youlehuo.app.R;

/**
 * Created by dayima on 17-1-13.
 */

public class CustomView extends View {

    private Paint paint;

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    /**
     * Paint主要负责设置绘图的风格，包括画笔的颜色，粗细，填充风格等，它有如下的一些设置方法
     * setARGB/setColor 设置颜色
     * setAlpha 设置透明度
     * setAntiAlias 设置是否抗锯齿
     * setShader 设置画笔的填充效果
     * setShadowLayer 设置阴影
     * setStyle 设置画笔风格 FILL FILL_AND_STROKE STROKE
     * setStrokeWidth 设置空心边框的宽度
     * setTextSize 设置绘制文本时文字的大小
     */
    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    /**
     * Canvas绘制方法：
     * drawArc 绘制弧
     * drawBitmap 绘制位图
     * drawCircle 绘制圆形
     * drawLine 绘制线
     * drawOval 绘制椭圆
     * drawPath 绘制路径
     * drawPoint 绘制一个点
     * drawPoints 绘制多个点
     * drawRect 绘制矩形
     * drawRoundRect 绘制圆角矩形
     * drawText 绘制字符串
     * drawTextOnPath 沿着路径绘制字符串
     * Canvas抗拒齿设置：
     * PaintFlagsDrawFilter pfd = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
     * canvas.setDrawFilter(pfd);
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.demo);
        canvas.drawBitmap(bitmap,0,0,paint);
        /**
         * 绘制圆形：drawCircle(float cx, float cy, float radius, Paint paint)
         cx： 圆心的x坐标
         cy： 圆心的y坐标
         radius： 圆的半径
         paint： 绘制风格
         */
        paint.setColor(0XFFFF4081);
        canvas.drawCircle(150,150,150,paint);
        paint.setStrokeWidth(20);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(470,150,150,paint);

        /**
         * 绘制矩形：drawRect(float left, float top, float right, float bottom, Paint paint) / drawRect(RectF rect, Paint paint)
         left： 矩形left的x坐标
         top： 矩形top的y坐标
         right： 矩形right的x坐标
         bottom： 矩形bottom的y坐标
         paint： 绘制风格
         */
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(0XFF303F9F);
        canvas.drawRect(0,350,350,700,paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(40);
        canvas.drawRect(390,350,740,700,paint);

        /**
         * 绘制圆角矩形：drawRoundRect(float left, float top, float right, float bottom, float rx, float ry, Paint paint) / drawRoundRect(RectF rect, float rx, float ry, Paint paint)

         left： 图形left的x坐标
         top： 图形top的y坐标
         right： 图形right的x坐标
         bottom： 图形bottom的y坐标
         rx： x方向的圆角半径
         ry： y方向的圆角半径
         paint > 绘制风格
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            paint.setStyle(Paint.Style.FILL);
            canvas.drawRoundRect(0, 800, 600, 1100, 30, 30, paint);

            paint.setStyle(Paint.Style.STROKE);
            canvas.drawRoundRect(700, 800, 1200, 1100, 30, 30, paint);
        }

        /**
         * 绘制椭圆：drawOval(float left, float top, float right, float bottom, Paint paint)

         left： 图形left的x坐标
         top： 图形top的y坐标
         right： 图形right的x坐标
         bottom： 图形bottom的y坐标
         paint： 绘制风格
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            paint.setStyle(Paint.Style.FILL);
            canvas.drawOval(0, 1200, 500, 1500, paint);

            paint.setStyle(Paint.Style.STROKE);
            canvas.drawOval(600, 1200, 1100, 1500, paint);
        }

        /**
         * 绘制弧：drawArc(RectF oval, float startAngle, float sweepAngle, boolean useCenter, Paint paint)

         oval： 指定圆弧的外轮廓矩形区域
         startAngle： 圆弧起始角度，单位为度
         sweepAngle： 圆弧扫过的角度，顺时针方向，单位为度
         useCenter： 如果为True时，在绘制圆弧时将圆心包括在内，通常用来绘制扇形
         paint： 绘制风格
         */
        paint.setStyle(Paint.Style.FILL);
        RectF rel = new RectF(0, 1600, 200, 1800);
        canvas.drawArc(rel, 0, 270, false, paint);
        //实心圆弧 将圆心包含在内
        RectF rel2 = new RectF(350, 1600, 550, 1800);
        canvas.drawArc(rel2, 0, 270, true, paint);
        //设置空心Style
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
        RectF rel3 = new RectF(560, 1600, 760, 1800);
        canvas.drawArc(rel3, 0, 270, false, paint);
        RectF rel4 = new RectF(770, 1600, 970, 1800);
        canvas.drawArc(rel4, 0, 270, true, paint);
        /**
         * 绘制文字：drawText(String text, float x, float y, Paint paint)

         text： 文本
         x： 文本origin的x坐标
         y： 文本baseline的y坐标
         paint： 绘制风格
         */
        paint.setStrokeWidth(10);
        paint.setTextSize(100);
        //绘制文本
        canvas.drawText("jEh", 80, 1900, paint);
    }
}
